package com.nlbdigit.payment_transfer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Data
@Entity
public class Account {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private BigDecimal balance;
}
