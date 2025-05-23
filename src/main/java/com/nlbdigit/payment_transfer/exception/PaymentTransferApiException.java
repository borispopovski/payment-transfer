package com.nlbdigit.payment_transfer.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import com.nlbdigit.payment_transfer.exception.constant.ExceptionConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentTransferApiException {
    SOURCE_ACCOUNT_NOT_FOUND_EXCEPTION(404, 404, ErrorType.NOT_FOUND.getDisplayName(), ExceptionConstants.SOURCE_ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE),
    DESTINATION_ACCOUNT_NOT_FOUND_EXCEPTION(404, 404, ErrorType.NOT_FOUND.getDisplayName(), ExceptionConstants.DESTINATION_ACCOUNT_NOT_FOUND_EXCEPTION_MESSAGE),
    INSUFFICIENT_BALANCE_EXCEPTION(400,400, ErrorType.BAD_REQUEST.getDisplayName(), ExceptionConstants.INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE),
    AMOUNT_POSITIVE_EXCEPTION(400, 400, ErrorType.BAD_REQUEST.getDisplayName(), ExceptionConstants.AMOUNT_POSITIVE_EXCEPTION_MESSAGE);

    private final Integer http;
    private final Integer code;
    private final String type;
    private final String message;

    public enum ErrorType {
        NOT_FOUND("Not Found"),
        BAD_REQUEST("Bad Request");

        private final String displayName;

        ErrorType(String displayName) {
            this.displayName = displayName;
        }

        @JsonValue
        public String getDisplayName() {
            return displayName;
        }
    }
}