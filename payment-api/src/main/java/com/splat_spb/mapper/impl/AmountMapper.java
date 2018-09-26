package com.splat_spb.mapper.impl;

import com.splat_spb.dto.payment.AmountDto;
import com.splat_spb.entity.Amount;
import com.splat_spb.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Default comment.
 **/
@Component
public class AmountMapper implements Mapper<AmountDto, Amount> {
    @Override
    public AmountDto mapToDto(Amount entity) {
        AmountDto dto = new AmountDto();
        dto.setValue(entity.getValue());
        dto.setCurrency(entity.getCurrency());
        return dto;
    }

    @Override
    public Amount mapToEntity(AmountDto dto) {
        Amount amount = new Amount();
        amount.setCurrency(dto.getCurrency());
        amount.setValue(dto.getValue());
        return amount;
    }
}
