package com.bank.gateway.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private BigDecimal amount;
    private String currency;
    private String senderIban;
    private String receiverIban;
    private String clientTaxNumber;
}
