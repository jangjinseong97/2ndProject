package com.green.jobdone.business.pic;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusinessPicPostReq {
    private long businessId;
    private List<String> pics;
}
