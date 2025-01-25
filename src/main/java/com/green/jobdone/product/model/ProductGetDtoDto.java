package com.green.jobdone.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGetDtoDto {
    private long optionDetailId;
    private String name;
    private int price;
    private String contents;
}
