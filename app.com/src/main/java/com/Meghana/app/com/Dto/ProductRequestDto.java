package com.Meghana.app.com.Dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int stock;
}
