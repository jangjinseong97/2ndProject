package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceGetOneRes {
    private long serviceId;
    private String userName;
    private String userPhone;
    private String businessName;
    private String reservedName;
    private String productName;
    private String address;
    private int price;
    private int completed;
    private String comment;
    private String addComment;
    private String startDate;
    private String endDate;
    private String mStartTime;
    private String mEndTime;
    private String businessNum;
    private String createdAt;
    private String categoryName;

    private List<ServicePhone> phone; // 있다 추가 확인
    private List<ServiceOptionDto> options;
}
