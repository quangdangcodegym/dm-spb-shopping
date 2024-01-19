package com.cg.shopping.model.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReqDto {
    private Long id;
    private String name;
    private double price;
    private Long categoryId;
}
