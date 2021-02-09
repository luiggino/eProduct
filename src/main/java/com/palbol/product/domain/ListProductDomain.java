package com.palbol.product.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
public class ListProductDomain implements Serializable {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_product;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "ratings_count")
    private Integer ratingsCount;

    @Column(name = "ratings_value")
    private Integer ratingsValue;

    @Column(name = "images")
    private String images;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "oldPrice")
    private String oldPrice;

    @Column(name = "newPrice")
    private String newPrice;

    @Column(name = "availibilityCount")
    private Integer availibilityCount;

    @Column(name = "cartCount")
    private Integer cartCount;

    @Column(name = "count")
    private Integer count;
}
