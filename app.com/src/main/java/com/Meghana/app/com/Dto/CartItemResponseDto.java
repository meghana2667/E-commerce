package com.Meghana.app.com.Dto;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
}
