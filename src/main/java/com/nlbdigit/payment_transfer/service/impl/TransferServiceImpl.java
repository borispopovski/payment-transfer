package com.nlbdigit.payment_transfer.service.impl;

import com.nlbdigit.payment_transfer.entity.Account;
import com.nlbdigit.payment_transfer.exception.ExceptionUtil;
import com.nlbdigit.payment_transfer.exception.PaymentTransferApiException;
import com.nlbdigit.payment_transfer.mapper.TransferMapper;
import com.nlbdigit.payment_transfer.model.AccountDto;
import com.nlbdigit.payment_transfer.model.TransferDto;
import com.nlbdigit.payment_transfer.repository.AccountRepository;
import com.nlbdigit.payment_transfer.repository.TransactionRepository;
import com.nlbdigit.payment_transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransferMapper transferMapper;

    @Override
    public void transfer(TransferDto transferDto) {
        String fromId = transferDto.getFromAccountId();
        String toId = transferDto.getToAccountId();
        BigDecimal amount = transferDto.getAmount();

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw ExceptionUtil.logAndBuildException(PaymentTransferApiException.AMOUNT_POSITIVE_EXCEPTION);

        Account fromAccount = accountRepository.findById(UUID.fromString(fromId))
                .orElseThrow(() -> ExceptionUtil.logAndBuildException(PaymentTransferApiException.SOURCE_ACCOUNT_NOT_FOUND_EXCEPTION));
        Account toAccount = accountRepository.findById(UUID.fromString(toId))
                .orElseThrow(() -> ExceptionUtil.logAndBuildException(PaymentTransferApiException.DESTINATION_ACCOUNT_NOT_FOUND_EXCEPTION));

        if (fromAccount.getBalance().compareTo(amount) < 0)
            throw ExceptionUtil.logAndBuildException(PaymentTransferApiException.INSUFFICIENT_BALANCE_EXCEPTION);

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        transactionRepository.save(transferMapper.toTransaction(UUID.randomUUID(), fromId, toId, amount, LocalDateTime.now()));
    }

    @Override
    public void add(AccountDto accountDto) {
        accountRepository.save(transferMapper.toAccount(UUID.randomUUID(), accountDto.getBalance()));
    }
}
