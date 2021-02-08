package com.palbol.product.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
public class ProductDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    private Integer oldPrice;

    @Column(name = "newPrice")
    private Integer newPrice;

    @Column(name = "availibilityCount")
    private Integer availibilityCount;

    @Column(name = "cartCount")
    private Integer cartCount;

    @Column(name = "count")
    private Integer count;
}
