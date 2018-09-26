package com.splat_spb.mapper.impl;

import com.splat_spb.dto.payment.PayerDto;
import com.splat_spb.dto.payment.PaymentDto;
import com.splat_spb.dto.payment.TransactionDto;
import com.splat_spb.entity.Payer;
import com.splat_spb.entity.Payment;
import com.splat_spb.entity.Transaction;
import com.splat_spb.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Default comment.
 **/
@Component
public class PaymentMapper implements Mapper<PaymentDto, Payment> {

    private final Mapper<PayerDto, Payer> payerMapper;
    private final Mapper<TransactionDto, Transaction> transactionMapper;

    public PaymentMapper(Mapper<PayerDto, Payer> payerMapper, Mapper<TransactionDto, Transaction> transactionMapper) {
        this.payerMapper = payerMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public PaymentDto mapToDto(Payment entity) {
        PaymentDto dto = new PaymentDto();
        dto.setCreateTime(entity.getCreateTime());
        dto.setId(entity.getId());
        dto.setState(entity.getState());
        dto.setIntent(entity.getIntent());
        dto.setNotificationUrl(entity.getNotificationUrl());
        dto.setPayer(payerMapper.mapToDto(entity.getPayer()));
        dto.setTransaction(transactionMapper.mapToDto(entity.getTransaction()));
        return dto;
    }

    @Override
    public Payment mapToEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setPayer(payerMapper.mapToEntity(dto.getPayer()));
        payment.setTransaction(transactionMapper.mapToEntity(dto.getTransaction()));
        payment.setNotificationUrl(dto.getNotificationUrl());
        payment.setIntent(dto.getIntent());
        payment.setState(dto.getState());
        return payment;
    }
}
