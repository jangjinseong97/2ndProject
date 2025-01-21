package com.green.jobdone.business.pic;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusinessPicPostRes {
    private long businessId;
    private List<String> pics;
}
