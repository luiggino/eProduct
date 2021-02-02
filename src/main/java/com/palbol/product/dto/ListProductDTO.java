package com.palbol.product.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ListProductDTO {
    Set<ProductDTO> listProduct;
    Integer count;
    Integer currentPage;

    public ListProductDTO() {
        this.listProduct = new HashSet<>();
    }
}
