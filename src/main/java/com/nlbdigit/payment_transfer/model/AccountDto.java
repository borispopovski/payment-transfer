package com.nlbdigit.payment_transfer.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    @NotNull
    private BigDecimal balance;
}
