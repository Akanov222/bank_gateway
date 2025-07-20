package com.bank.gateway.service;

import com.bank.gateway.model.Payment;
import com.bank.gateway.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment createPayment(Payment payment) {
        if(payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма платежа должна быть > 0");
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByCurrency(String currency) {
        return paymentRepository.findByCurrency(currency);
    }
}
