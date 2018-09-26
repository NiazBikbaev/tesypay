package com.splat_spb.dao;


import com.splat_spb.dto.payment.PaymentDto;

import java.util.Collection;

/**
 * Default comment.
 **/
public interface PaymentDao {
    PaymentDto save(PaymentDto paymentDto);

    Collection<PaymentDto> findAll();

    PaymentDto findOne(String id);
}
