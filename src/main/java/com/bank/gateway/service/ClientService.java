package com.bank.gateway.service;

import com.bank.gateway.model.Client;
import com.bank.gateway.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        if(clientRepository.existByTaxNumber(client.getTaxNumber())) {
            throw new IllegalStateException("Клиент с таким ИНН уже существует");
        }
        return clientRepository.save(client);
    }
}
