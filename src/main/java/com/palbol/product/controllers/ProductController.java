package com.palbol.product.controllers;

import com.palbol.product.dto.ListProductDTO;
import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;
import com.palbol.product.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@Tag(name = "Product", description = "Apis de Products de la APP")
@Slf4j
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/findall")
    public ResponseEntity<ListProductDTO> findAll(@RequestBody PageDTO pageable) {
        try {
            ListProductDTO response = this.productService.listAll(pageable);

            log.debug(response.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findbyid")
    public ResponseEntity<ProductDTO> findById(@RequestParam("id") long id) {
        try {
            ProductDTO response = this.productService.getById(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
