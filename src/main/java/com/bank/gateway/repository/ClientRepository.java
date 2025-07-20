package com.bank.gateway.repository;

import com.bank.gateway.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existByTaxNumber(String taxNumber); // Найти клиента по ИНН
}
