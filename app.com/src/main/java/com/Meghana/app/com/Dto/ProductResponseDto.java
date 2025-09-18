package com.Meghana.app.com.Dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String companyName;
}
