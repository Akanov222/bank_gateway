package com.bank.gateway.dto;

import com.bank.gateway.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(PaymentRequest request);
    PaymentResponse toResponse(Payment payment);
}
