package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ServiceGetRes {
    private long serviceId;
    private String userName;
    private String reservedName;
    private String businessName;
    private String productName;
    private int price;
    private int completed;
    private String startDate;
    private String createdAt;
    private String address;
}
