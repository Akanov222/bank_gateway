package com.bank.gateway.service;

import com.bank.gateway.dto.PaymentMapper;
import com.bank.gateway.dto.PaymentRequest;
import com.bank.gateway.dto.PaymentResponse;
import com.bank.gateway.model.Payment;
import com.bank.gateway.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ProducerTemplate producerTemplate; // Camel

    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        Payment payment = paymentMapper.toEntity(request);
        producerTemplate.sendBody("direct:processPayment", payment); // Запускаем Camel route
//        Payment savePayment = paymentRepository.save(payment);
//        return paymentMapper.toResponse(savePayment);
        return paymentMapper.toResponse(payment);
    }


    public List<Payment> getPaymentsByCurrency(String currency) {
        return paymentRepository.findByCurrency(currency);
    }
}
