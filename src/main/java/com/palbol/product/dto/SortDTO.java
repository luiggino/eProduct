package com.palbol.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@Data
public class SortDTO {
    private Sort.Direction direction;
    private String property;

}
