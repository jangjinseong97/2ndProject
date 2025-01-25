package com.green.jobdone.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductGetDto {
    private long productOptionId;
    private String productOptionName;
    private List<ProductGetDtoDto> optionDetailList;

}
