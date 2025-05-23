package com.nlbdigit.payment_transfer;

import com.nlbdigit.payment_transfer.entity.Account;
import com.nlbdigit.payment_transfer.entity.Transaction;
import com.nlbdigit.payment_transfer.mapper.TransferMapper;
import com.nlbdigit.payment_transfer.model.TransferDto;
import com.nlbdigit.payment_transfer.repository.AccountRepository;
import com.nlbdigit.payment_transfer.repository.TransactionRepository;
import com.nlbdigit.payment_transfer.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentTransferApplicationTests {

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private TransferMapper transferMapper;

	@InjectMocks
	private TransferServiceImpl transferService;

	private Account fromAccount;
	private Account toAccount;
	private Transaction transaction;
	private TransferDto transferDto;
	private UUID fromId;
	private UUID toId;

	@BeforeEach
	void setUp() {
		fromId = UUID.randomUUID();
		toId = UUID.randomUUID();

		fromAccount = new Account();
		toAccount = new Account();

		transaction = new Transaction();
		transferDto = new TransferDto();
	}

	@Test
	void testSuccessfulTransfer() {
		BigDecimal amount = new BigDecimal("100.00");

		fromAccount = transferMapper.toAccount(fromId, new BigDecimal("200.00"));
		toAccount = transferMapper.toAccount(toId, new BigDecimal("50.00"));

		when(accountRepository.findById(fromId)).thenReturn(Optional.of(fromAccount));
		when(accountRepository.findById(toId)).thenReturn(Optional.of(toAccount));

		transaction = new Transaction();
		when(transferMapper.toTransaction(any(), anyString(), anyString(), any(), any()))
				.thenReturn(transaction);

		transferDto = transferMapper.toTransferDto(fromId.toString(), toId.toString(), amount);
		transferService.transfer(transferDto);

		assertThat(fromAccount.getBalance()).isEqualByComparingTo("100.00");
		assertThat(toAccount.getBalance()).isEqualByComparingTo("150.00");

		verify(accountRepository).save(fromAccount);
		verify(accountRepository).save(toAccount);
		verify(transactionRepository).save(transaction);
	}

	@Test
	void testTransferFailsForInsufficientBalance() {
		BigDecimal amount = new BigDecimal("300.00");

		fromAccount = transferMapper.toAccount(fromId, new BigDecimal("200.00"));
		toAccount = transferMapper.toAccount(toId, new BigDecimal("50.00"));

		when(accountRepository.findById(fromId)).thenReturn(Optional.of(fromAccount));
		when(accountRepository.findById(toId)).thenReturn(Optional.of(toAccount));

		transferDto = transferMapper.toTransferDto(fromId.toString(), toId.toString(), amount);

		assertThatThrownBy(() -> transferService.transfer(transferDto))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Insufficient balance");
	}

	@Test
	void testTransferFailsForInvalidAmount() {
		transferDto = transferMapper.toTransferDto(UUID.randomUUID().toString(), UUID.randomUUID().toString(), BigDecimal.ZERO);

		assertThatThrownBy(() -> transferService.transfer(transferDto))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Amount must be positive");
	}

	@Test
	void testTransferFailsWhenSourceAccountNotFound() {
		BigDecimal amount = new BigDecimal("50.00");

		when(accountRepository.findById(fromId)).thenReturn(Optional.empty());

		transferDto = transferMapper.toTransferDto(fromId.toString(), toId.toString(), amount);

		assertThatThrownBy(() -> transferService.transfer(transferDto))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Source account not found");
	}

}
