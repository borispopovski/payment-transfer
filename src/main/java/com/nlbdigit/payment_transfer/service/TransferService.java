package com.nlbdigit.payment_transfer.service;

import com.nlbdigit.payment_transfer.model.AccountDto;
import com.nlbdigit.payment_transfer.model.TransferDto;

public interface TransferService {

    void transfer(TransferDto transferDto);
    void add(AccountDto accountDto);
}
