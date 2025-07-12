package banking_gateway.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String taxNumber; //INN

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Payment> payments;

}
