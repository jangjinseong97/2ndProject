package com.green.jobdone.service.model.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoPayDto {
    private long userId;
    private String productName;
    private int price;

}
