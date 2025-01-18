package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ServiceGetRes {
    private String userName;
    private String businessName;
    private String productName;
    private String startTime;
    private String endTime;
    private String mStartTime;
    private String mEndTime;
    private int allow;
    private String userPhone;
    private int price;
    private int completed;
    private long serviceId;
    private String address;
    private String comment;
    private String addComment;
    private List<String> businessPhone;
    private List<ServiceOptionDto> options;
}
