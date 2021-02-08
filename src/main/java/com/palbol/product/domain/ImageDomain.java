package com.palbol.product.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Image")
@NoArgsConstructor
@Data
public class ImageDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parent;

    private String url;

    @Enumerated(EnumType.ORDINAL)
    private ImageType type;

    private Date created_at;

    public ImageDomain(Long id, Long parent, String url, ImageType type) {
        this.id = id;
        this.parent = parent;
        this.url = url;
        this.type = type;
        this.created_at = new Date();
    }

    public ImageDomain(Long id, String url, ImageType type) {
        this.id = id;
        this.parent = null;
        this.url = url;
        this.type = type;
        this.created_at = new Date();
    }
}
