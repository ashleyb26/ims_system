package com.example.ims.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private int quantity;
    @NotEmpty
    private float price;

}
