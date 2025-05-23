package com.nlbdigit.payment_transfer.controller;

import com.nlbdigit.payment_transfer.model.AccountDto;
import com.nlbdigit.payment_transfer.model.TransferDto;
import com.nlbdigit.payment_transfer.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransferController {

    final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody @Valid TransferDto transferDto) {
        transferService.transfer(transferDto);
        return ResponseEntity.ok("Transfer successful");
    }

    @PostMapping("/account")
    public ResponseEntity<String> add(@RequestBody @Valid AccountDto accountDto) {
        transferService.add(accountDto);
        return ResponseEntity.ok("Account is added");
    }
}
