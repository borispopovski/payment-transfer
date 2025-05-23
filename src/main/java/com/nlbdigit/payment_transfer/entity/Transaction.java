package com.nlbdigit.payment_transfer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Transaction {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime timestamp;

}
