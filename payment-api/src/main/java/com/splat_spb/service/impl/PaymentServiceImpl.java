package com.splat_spb.service.impl;

import com.spb_splat.checksum.ChecksumCalculator;
import com.spb_splat.dto.WebHookEventDto;
import com.splat_spb.dao.PaymentDao;
import com.splat_spb.dto.payment.AmountDto;
import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.event.PaymentApprovedEvent;
import com.splat_spb.event.PaymentFailedEvent;
import com.splat_spb.service.EventService;
import com.splat_spb.service.PaymentService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * {@link PaymentService} implementation.
 *
 * @author Niyaz_Bikbaev
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentDao paymentDao;
    private final EventService eventService;
    private final ChecksumCalculator checksumCalculator;
    private final ApplicationEventPublisher eventPublisher;
    @Value("${testpay.secret}")
    private String secret;

    public PaymentServiceImpl(PaymentDao paymentDao,
                              EventService eventService,
                              ChecksumCalculator checksumCalculator,
                              ApplicationEventPublisher eventPublisher) {
        this.paymentDao = paymentDao;
        this.eventService = eventService;
        this.checksumCalculator = checksumCalculator;
        this.eventPublisher = eventPublisher;
    }

    public PaymentDto save(PaymentDto paymentDto) {
        return paymentDao.save(paymentDto);
    }

    @Async
    @Override
    public void processPayment(PaymentDto paymentDto) throws SchedulerException {
        String notificationUrl = paymentDto.getNotificationUrl();
        ApplicationEvent event;

        try {
            Thread.sleep(1000);// business logic imitation
            event = new PaymentApprovedEvent(paymentDto);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Exception occurred while processing payment", e);
            }
            event = new PaymentFailedEvent(paymentDto);
        }
        publish(event);
        WebHookEventDto eventDto = prepareWebHookEvent(paymentDao.findOne(paymentDto.getId()));
        eventService.publishEvent(notificationUrl, eventDto);
    }

    private WebHookEventDto prepareWebHookEvent(PaymentDto paymentDto) {
        WebHookEventDto eventDto = mapToWebHookEvent(paymentDto);
        String rawString = prepareForHashing(eventDto);
        String checksum = checksumCalculator.calculateChecksum(rawString.getBytes());
        eventDto.setSignature(checksum);
        return eventDto;
    }

    private void publish(ApplicationEvent event) {
        eventPublisher.publishEvent(event);
    }

    private WebHookEventDto mapToWebHookEvent(PaymentDto paymentDto) {
        WebHookEventDto eventDto = new WebHookEventDto();
        AmountDto amount = paymentDto.getTransaction().getAmount();
        eventDto.setId(paymentDto.getId());
        eventDto.setStatus(paymentDto.getState());
        eventDto.setAmount(amount.getValue());
        eventDto.setCurrency(amount.getCurrency());
        eventDto.setExternalId(paymentDto.getTransaction().getExternalId());
        return eventDto;
    }

    private String prepareForHashing(WebHookEventDto eventDto) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(eventDto.getCurrency());
        joiner.add(eventDto.getAmount());
        joiner.add(checksumCalculator.calculateChecksum(secret.getBytes(US_ASCII)).toUpperCase());
        joiner.add(eventDto.getId());
        joiner.add(Optional.ofNullable(eventDto.getExternalId()).orElse(""));
        joiner.add(eventDto.getStatus().toString().toLowerCase());
        return joiner.toString();
    }

}
