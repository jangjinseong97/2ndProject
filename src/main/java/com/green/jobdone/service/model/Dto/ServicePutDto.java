package com.green.jobdone.service.model.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePutDto {
    private Long serviceOptionId;
    private Long optionDetailId;
    private String comment;
    private int price;
}
