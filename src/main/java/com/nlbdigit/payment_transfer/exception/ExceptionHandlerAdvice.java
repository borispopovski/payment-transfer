package com.nlbdigit.payment_transfer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler
    public ResponseEntity<ApplicationError> handleSPE(ServiceProcessingException cause) {
        ApplicationError applicationError = ApplicationError.builder()
                .code(cause.getCode())
                .type(cause.getType())
                .message(cause.getMessage())
                .build();
        log.error("Application Exception with cause: ", cause);
        return ResponseEntity.status(cause.getHttp()).body(applicationError);
    }
}