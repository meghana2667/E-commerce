package com.Meghana.app.com.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String companyName;
    private String address;
    private Long phoneNumber;

    @OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
