package com.Meghana.app.com.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartitem")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    @ManyToOne
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    private double totalPrice;

    private int quantity;
}
