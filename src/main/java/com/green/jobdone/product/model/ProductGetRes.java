package com.green.jobdone.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductGetRes {
    private long productId;
    private int price;
    private List<ProductGetDto> optionList;
}
