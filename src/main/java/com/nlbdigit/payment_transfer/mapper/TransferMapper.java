package com.nlbdigit.payment_transfer.mapper;

import com.nlbdigit.payment_transfer.entity.Account;
import com.nlbdigit.payment_transfer.entity.Transaction;
import com.nlbdigit.payment_transfer.model.TransferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fromAccount", source = "fromId")
    @Mapping(target = "toAccount", source = "toId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "timestamp", source = "timestamp")
    Transaction toTransaction(UUID id, String fromId, String toId, BigDecimal amount, LocalDateTime timestamp);

    @Mapping(target = "fromAccountId", source = "fromId")
    @Mapping(target = "toAccountId", source = "toId")
    @Mapping(target = "amount", source = "amount")
    TransferDto toTransferDto(String fromId, String toId, BigDecimal amount);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "balance", source = "balance")
    Account toAccount(UUID id, BigDecimal balance);

}
