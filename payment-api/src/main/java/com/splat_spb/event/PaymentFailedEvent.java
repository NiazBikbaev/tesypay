package com.splat_spb.event;

import com.splat_spb.dto.payment.PaymentDto;
import org.springframework.context.ApplicationEvent;

/**
 * Default comment.
 **/
public class PaymentFailedEvent extends ApplicationEvent {

    public PaymentFailedEvent(Object source) {
        super(source);
    }

    @Override
    public PaymentDto getSource() {
        return (PaymentDto) super.getSource();
    }
}
