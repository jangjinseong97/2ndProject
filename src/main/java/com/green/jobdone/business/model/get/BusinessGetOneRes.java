package com.green.jobdone.business.model.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessGetOneRes {
    private String logo;
    private long detailTypeId;
    private String detailTypeName;
    private long businessId;
    private String businessName;
    private String title;
    private double scoreAvg;
    private int priceMin;
    private int like;
    private String address;
    private int serviceCount;
    private String openTime;
    private String closeTime;
    private String busiCreatedAt;
    private String contents;
}
