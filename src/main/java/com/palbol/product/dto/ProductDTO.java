package com.palbol.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("images")
    private List<ImageDTO> images;
    @JsonProperty("oldPrice")
    private String oldPrice;
    @JsonProperty("newPrice")
    private String newPrice;
    @JsonProperty("discount")
    private Integer discount;
    @JsonProperty("ratingsCount")
    private Integer ratingsCount;
    @JsonProperty("ratingsValue")
    private Integer ratingsValue;
    @JsonProperty("description")
    private String description;
    @JsonProperty("availibilityCount")
    private Integer availibilityCount;
    @JsonProperty("cartCount")
    private Integer cartCount;
    @JsonProperty("color")
    private List<String> color;
    @JsonProperty("size")
    private List<String> size;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("categoryId")
    private Integer categoryId;
}
