package com.bank.gateway.controller;

import com.bank.gateway.dto.PaymentRequest;
import com.bank.gateway.dto.PaymentResponse;
import com.bank.gateway.model.Payment;
import com.bank.gateway.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setSenderIban(request.getSenderIban());
        payment.setReceiverIban(request.getReceiverIban());

        Payment savedPayment = paymentService.createPayment(payment);
        return convertToResponse(savedPayment);
    }

    private PaymentResponse convertToResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setCreatedAt(payment.getCreatedAt());
        return response;
    }
}
