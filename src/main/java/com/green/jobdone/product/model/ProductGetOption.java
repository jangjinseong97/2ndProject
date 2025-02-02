package com.green.jobdone.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductGetOption {
    private long detailTypeId;
    private String detailTypeName;
    private List<ProductGetOptionDto> detailTypeOptions;
}
