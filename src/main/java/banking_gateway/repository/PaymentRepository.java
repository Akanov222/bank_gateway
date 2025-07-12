package banking_gateway.repository;

import banking_gateway.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByCurrency(String currency); // Найти платежи по валюте
}
