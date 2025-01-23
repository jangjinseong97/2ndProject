package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePutDto {
    private long serviceOptionId;
    private String comment;
    private int price;
}
