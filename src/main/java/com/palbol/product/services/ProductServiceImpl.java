package com.palbol.product.services;

import com.palbol.product.domain.ImageDomain;
import com.palbol.product.domain.ImageType;
import com.palbol.product.domain.ListProductDomain;
import com.palbol.product.dto.ImageDTO;
import com.palbol.product.dto.ListProductDTO;
import com.palbol.product.dto.PageDTO;
import com.palbol.product.dto.ProductDTO;
import com.palbol.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListProductDTO listAll(PageDTO pageDTO) {
        log.info("listAll");

        Pageable pageable = PageRequest.of(pageDTO.getPageNo() - 1,
                pageDTO.getPageSize());

        List<ListProductDomain> response = new ArrayList<>();
        try {
            response = this.productRepository
                    .findAllBetweenStoredProcedure(
                            pageable.getPageSize(),
                            Long.valueOf(pageable.getOffset()).intValue(),
                            pageDTO.getSortBy().getProperty(),
                            pageDTO.getSortBy().getDirection().name());

        } catch (Exception err) {
            log.error(err.getMessage(), err);
        }

        ListProductDomain firstProduct = response.stream().findFirst().orElse(null);
        Integer count = firstProduct != null ? firstProduct.getCount() : 0;

        ModelMapper modelMapper = new ModelMapper();
        Set<ProductDTO> productDTOSet = response
                .stream()
                .map(productDomain -> {
                    ProductDTO productDTO = modelMapper.map(productDomain, ProductDTO.class);

                    return convertToProductDTO(productDomain, productDTO);
                })
                .collect(Collectors.toSet());

        ListProductDTO listProductDTO = new ListProductDTO();
        listProductDTO.setListProduct(productDTOSet);
        listProductDTO.setCount(count);
        listProductDTO.setCurrentPage(pageable.getPageSize());

        return listProductDTO;
    }

    @Override
    public ProductDTO getById(Long id) {
        log.info("getById " + id);
        try {
            ListProductDomain productDomain = productRepository.findByStoredProcedure(id.intValue());

            ModelMapper modelMapper = new ModelMapper();
            ProductDTO productDTO = modelMapper.map(productDomain, ProductDTO.class);

            return convertToProductDTO(productDomain, productDTO);
        } catch (Exception err) {
            log.error(err.getMessage(), err);
        }

        return null;
    }

    private ProductDTO convertToProductDTO(ListProductDomain productDomain, ProductDTO productDTO) {
        List<ImageDomain> listImageDomain = getListImageDomain(productDomain.getImages());
        List<ImageDTO> listImageDTO = this.getListImageDTO(listImageDomain);
        List<String> listColor = this.getListColor(productDomain.getColor());
        List<String> listSize = this.getListSize(productDomain.getSize());

        productDTO.setImages(listImageDTO);
        productDTO.setColor(listColor);
        productDTO.setSize(listSize);

        int newPrice = 0;
        if (productDomain.getNewPrice() != null && !productDomain.getNewPrice().isEmpty()) {
            newPrice = Integer.parseInt(productDomain.getNewPrice());
        }

        int oldPrice = 0;
        if (productDomain.getOldPrice() != null && !productDomain.getOldPrice().isEmpty()) {
            oldPrice = Integer.parseInt(productDomain.getOldPrice());
        }

        int discount = newPrice - oldPrice;
        productDTO.setDiscount(discount);

        return productDTO;
    }

    private List<ImageDomain> getListImageDomain(String images) {
        if (images == null || images.isEmpty()) {
            return new ArrayList<>();
        }
        String[] split = images.split(";");

        return Arrays.stream(split)
                .map(x -> {
                    String[] image = x.split(",");
                    int typeValue = Integer.parseInt(image[3]);

                    if (image[1].isEmpty()) {
                        return new ImageDomain(Long.valueOf(image[0]),
                                image[2],
                                ImageType.values()[typeValue]);
                    }
                    return new ImageDomain(Long.valueOf(image[0]),
                            Long.valueOf(image[1]),
                            image[2],
                            ImageType.values()[typeValue]);
                }).collect(Collectors.toList());
    }

    private List<ImageDTO> getListImageDTO(List<ImageDomain> listImageDomain) {
        return listImageDomain.stream()
                .filter(x -> x.getParent() == null)
                .map(parent -> {
                    List<ImageDomain> collect = listImageDomain.stream()
                            .filter(child -> child.getParent() != null && child.getParent().equals(parent.getId()))
                            .collect(Collectors.toList());

                    String small = collect.stream()
                            .filter(child -> child.getType().equals(ImageType.SMALL))
                            .map(ImageDomain::getUrl)
                            .findFirst().orElse("");
                    small = small.isEmpty() ? parent.getUrl() : small;

                    String medium = collect.stream()
                            .filter(child -> child.getType().equals(ImageType.MEDIUM))
                            .map(ImageDomain::getUrl)
                            .findFirst().orElse("");
                    medium = medium.isEmpty() ? parent.getUrl() : medium;

                    String big = collect.stream()
                            .filter(child -> child.getType().equals(ImageType.BIG))
                            .map(ImageDomain::getUrl)
                            .findFirst().orElse("");
                    big = big.isEmpty() ? parent.getUrl() : big;

                    return new ImageDTO(small, medium, big);
                }).collect(Collectors.toList());
    }

    private List<String> getListColor(String color) {
        if (color == null || color.isEmpty()) {
            return new ArrayList<>();
        }
        String[] split = color.split(";");
        return Arrays.stream(split)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());
    }

    private List<String> getListSize(String size) {
        if (size == null || size.isEmpty()) {
            return new ArrayList<>();
        }
        String[] split = size.split(";");
        return Arrays.stream(split)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());
    }
}
