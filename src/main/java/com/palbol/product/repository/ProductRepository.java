package com.palbol.product.repository;

import com.palbol.product.domain.ProductDomain;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductDomain, Long> {
}
