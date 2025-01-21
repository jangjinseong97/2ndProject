package com.green.jobdone.like.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeGetRes {
    private long businessId;
    private String pic;
    private String title;
    private String businessName;
    private int price;
    private double score;
    private int reviewNumbers;


}
