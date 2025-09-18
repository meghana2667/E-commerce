package com.Meghana.app.com.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Long phoneNumber;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private  Cart cart;
}
