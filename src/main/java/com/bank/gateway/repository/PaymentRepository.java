package com.bank.gateway.repository;

import com.bank.gateway.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByCurrency(String currency); // Найти платежи по валюте
}
