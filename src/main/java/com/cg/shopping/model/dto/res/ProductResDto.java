package com.cg.shopping.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {
    private Long id;
    private String name;
    private double price;

    private CategoryResDto category;

}
