package com.bank.gateway.repository;

import com.bank.gateway.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String> {
    List<Client> findByTaxNumber(String taxNumber); // Найти клиента по ИНН
}
