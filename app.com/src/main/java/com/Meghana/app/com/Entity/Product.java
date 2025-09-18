package com.Meghana.app.com.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String productName;
    private String description;
    private double price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;

}
