package com.palbol.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {
    @JsonProperty("small")
    private String small;
    @JsonProperty("medium")
    private String medium;
    @JsonProperty("big")
    private String big;
}
