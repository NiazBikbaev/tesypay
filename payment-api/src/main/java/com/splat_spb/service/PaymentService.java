package com.splat_spb.service;

import com.splat_spb.dto.payment.PaymentDto;
import org.quartz.SchedulerException;

public interface PaymentService {
    PaymentDto save(PaymentDto paymentDto);

    void processPayment(PaymentDto paymentDto) throws SchedulerException;
}
