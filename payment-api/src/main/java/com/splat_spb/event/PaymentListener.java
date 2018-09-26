package com.splat_spb.event;

import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.service.PaymentService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.spb_splat.dto.State.APPROVED;
import static com.spb_splat.dto.State.CREATED;
import static com.spb_splat.dto.State.FAILED;

/**
 * Default comment.
 **/
@Component
public class PaymentListener {

    private final PaymentService paymentService;

    public PaymentListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @TransactionalEventListener
    public void paymentCreatedListener(PaymentCreatedEvent event) {
        PaymentDto source = event.getSource();
        source.setState(CREATED);
        paymentService.save(source);
    }

    @EventListener
    public void paymentApprovedListener(PaymentApprovedEvent event) {
        PaymentDto source = event.getSource();
        source.setState(APPROVED);
        paymentService.save(source);
    }

    @EventListener
    public void paymentFailedListener(PaymentFailedEvent event) {
        PaymentDto source = event.getSource();
        source.setState(FAILED);
        paymentService.save(source);
    }
}
