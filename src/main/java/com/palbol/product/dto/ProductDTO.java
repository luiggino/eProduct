package com.palbol.product.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ProductDTO {
    private Integer id;

    @NotNull
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    private Integer ranting;
}
