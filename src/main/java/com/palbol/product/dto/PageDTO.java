package com.palbol.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private Integer pageNo;
    private Integer pageSize;
    private SortDTO sortBy;
    private String search;
}
