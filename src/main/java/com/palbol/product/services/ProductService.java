package com.palbol.product.services;

import com.palbol.product.dto.ListProductDTO;
import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;

public interface ProductService {
    ListProductDTO listAll(PageDTO pageDTO);

    ProductDTO getById(Long id);
}
