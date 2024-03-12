package com.example.cart.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class productdto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private double price;
}