package com.green.jobdone.business.model.get;

import com.green.jobdone.common.PicUrlMaker;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BusinessGetRes {
    private long categoryId;
    private String categoryName;
    private long detailTypeId;
    private String detailTypeName;
    private String pic;
    private long businessId;
    private String businessName;
    private String title;
    private double scoreAvg;
    private int price;
    private int like;
    private int reviewCount;
    private long regionId;
    private String region;
    private int orderCount;



    public BusinessGetRes(long categoryId,String categoryName, long detailTypeId, String detailTypeName,String pic, long businessId, String businessName
            , String title, int price, double scoreAvg, int like, int reviewCount, long regionId, String region, int orderCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.detailTypeId = detailTypeId;
        this.detailTypeName = detailTypeName;
        this.pic = PicUrlMaker.makePicUrl(businessId, pic);
        this.businessId = businessId;
        this.businessName = businessName;
        this.title = title;
        this.price = price;
        this.scoreAvg = scoreAvg;
        this.like = like;
        this.reviewCount = reviewCount;
        this.regionId = regionId;
        this.region = region;
        this.orderCount = orderCount;
    }
}
