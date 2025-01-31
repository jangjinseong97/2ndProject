package com.green.jobdone.service.model.Dto;

import lombok.Data;

@Data
public class KakaoPayReq {
    private String pgToken;
    private Long serviceId;
    private String tid;
}
