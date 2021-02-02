package com.palbol.product.services;

import com.palbol.product.domain.ProductDomain;
import com.palbol.product.dto.ListProductDTO;
import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;
import com.palbol.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListProductDTO listAll(PageDTO pageDTO) {
        log.info("listAll");
        Pageable pageable = PageRequest.of(pageDTO.getPageNo() - 1,
                pageDTO.getPageSize());

        List<ProductDomain> response = this.productRepository
                .findAllBetweenStoredProcedure(
                        pageable.getPageSize(),
                        Long.valueOf(pageable.getOffset()).intValue(),
                        pageDTO.getSortBy().getProperty(),
                        pageDTO.getSortBy().getDirection().name());

        ProductDomain firstProduct = response.stream().findFirst().orElse(null);
        Integer count = firstProduct != null ? firstProduct.getCount() : 0;

        ModelMapper modelMapper = new ModelMapper();
        Set<ProductDTO> productDTOSet = response
                .stream()
                .map(productDomain -> modelMapper.map(productDomain, ProductDTO.class))
                .collect(Collectors.toSet());

        ListProductDTO listProductDTO = new ListProductDTO();
        listProductDTO.setListProduct(productDTOSet);
        listProductDTO.setCount(count);
        listProductDTO.setCurrentPage(pageable.getPageSize());

        return listProductDTO;
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
