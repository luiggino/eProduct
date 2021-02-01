package com.palbol.product.services;

import com.palbol.product.domain.ProductDomain;
import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;
import com.palbol.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<ProductDTO> listAll(PageDTO pageDTO) {
        Pageable pageable = PageRequest.of(pageDTO.getPageNo(),
                pageDTO.getPageSize(),
                Sort.by(pageDTO.getSortBy()));
        Set<ProductDTO> products = new HashSet<>();
        Page<ProductDomain> pagedResult = productRepository.findAll(pageable);

        if (pagedResult.hasContent()) {
            ModelMapper modelMapper = new ModelMapper();
            return pagedResult.getContent()
                    .stream()
                    .map(productDomain -> modelMapper.map(productDomain, ProductDTO.class))
                    .collect(Collectors.toSet());
        }
        return products;
    }

    @Override
    public ProductDTO getById(Long id) {
        ProductDomain productDomain = productRepository.findById(id).orElse(null);
        if (productDomain != null) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(productDomain, ProductDTO.class);
        }
        return null;
    }
}
