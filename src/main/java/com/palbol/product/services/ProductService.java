package com.palbol.product.services;

import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;

import java.util.Set;

public interface ProductService {
    Set<ProductDTO> listAll(PageDTO pageDTO);

    ProductDTO getById(Long id);
}
