package com.splat_spb.mapper.impl;

import com.splat_spb.dto.payment.AmountDto;
import com.splat_spb.dto.payment.TransactionDto;
import com.splat_spb.entity.Amount;
import com.splat_spb.entity.Transaction;
import com.splat_spb.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Default comment.
 **/
@Component
public class TransactionMapper implements Mapper<TransactionDto, Transaction> {

    private final Mapper<AmountDto, Amount> amountMapper;

    public TransactionMapper(Mapper<AmountDto, Amount> amountMapper) {
        this.amountMapper = amountMapper;
    }

    @Override
    public TransactionDto mapToDto(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        AmountDto amountDto = amountMapper.mapToDto(entity.getAmount());
        dto.setAmount(amountDto);
        dto.setDescription(entity.getDescription());
        dto.setExternalId(entity.getExternalId());
        return dto;
    }

    @Override
    public Transaction mapToEntity(TransactionDto dto) {
        Amount amount = amountMapper.mapToEntity(dto.getAmount());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(dto.getDescription());
        transaction.setExternalId(dto.getExternalId());
        return transaction;
    }
}
