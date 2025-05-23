package com.nlbdigit.payment_transfer.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ExceptionUtil {
    public ServiceProcessingException logAndBuildException(
            PaymentTransferApiException paymentTransferApiException) {
        log.error(getLogErrorMessage(paymentTransferApiException));
        return new ServiceProcessingException(
                paymentTransferApiException.getHttp(),
                paymentTransferApiException.getHttp(),
                paymentTransferApiException.getType(),
                paymentTransferApiException.getMessage());
    }

    private String getLogErrorMessage(PaymentTransferApiException paymentTransferApiException) {
        return paymentTransferApiException.getCode()
                + " - "
                + paymentTransferApiException.getMessage();
    }
}