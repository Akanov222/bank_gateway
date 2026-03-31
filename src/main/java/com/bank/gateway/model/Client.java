
package com.bank.gateway.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = client)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id")
    private String clientId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, name = "tax_number")
    private String taxNumber; //INN

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accountList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Payment> paymentList;
}
