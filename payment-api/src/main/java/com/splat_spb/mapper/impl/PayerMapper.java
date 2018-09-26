package com.splat_spb.mapper.impl;

import com.splat_spb.dto.payment.PayerDto;
import com.splat_spb.entity.Payer;
import com.splat_spb.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Default comment.
 **/
@Component
public class PayerMapper<D, E> implements Mapper<PayerDto, Payer> {
    @Override
    public PayerDto mapToDto(Payer entity) {
        PayerDto payerDto = new PayerDto();
        payerDto.setEmail(entity.getEmail());
        return payerDto;
    }

    @Override
    public Payer mapToEntity(PayerDto dto) {
        Payer payer = new Payer();
        payer.setEmail(dto.getEmail());
        return payer;
    }
}
