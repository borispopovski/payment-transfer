package com.nlbdigit.payment_transfer.exception.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExceptionConstants {
    public static final String SOURCE_ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "Source account not found";
    public static final String DESTINATION_ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE = "Destination account not found";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE = "Insufficient balance";
    public static final String AMOUNT_POSITIVE_EXCEPTION_MESSAGE = "Amount must be positive";
}