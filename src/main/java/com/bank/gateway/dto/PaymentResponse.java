package com.bank.gateway.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private String id;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime createdAt;
}
