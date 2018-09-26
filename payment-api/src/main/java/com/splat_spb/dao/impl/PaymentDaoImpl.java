package com.splat_spb.dao.impl;

import com.splat_spb.dao.PaymentDao;
import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.entity.Payment;
import com.splat_spb.event.PaymentCreatedEvent;
import com.splat_spb.mapper.Mapper;
import com.splat_spb.repository.PaymentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Default comment.
 **/
@Component
public class PaymentDaoImpl implements PaymentDao {

    private final Mapper<PaymentDto, Payment> mapper;
    private final PaymentRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public PaymentDaoImpl(Mapper<PaymentDto, Payment> paymentMapper,
                          PaymentRepository paymentRepository,
                          ApplicationEventPublisher eventPublisher) {
        this.mapper = paymentMapper;
        this.repository = paymentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public PaymentDto save(PaymentDto paymentDto) {
        Payment payment = mapper.mapToEntity(paymentDto);
        Payment savedPayment = repository.save(payment);
        PaymentDto mappedDto = mapper.mapToDto(savedPayment);
        eventPublisher.publishEvent(new PaymentCreatedEvent(mappedDto));
        return mappedDto;
    }

    @Override
    public Collection<PaymentDto> findAll() {
        return repository.findAll().stream()
                         .map(mapper::mapToDto)
                         .collect(Collectors.toList());
    }

    @Override
    public PaymentDto findOne(String id) {
        return repository.findById(id)
                         .map(mapper::mapToDto)
                         .orElse(null);
    }
}
