package com.green.jobdone.business.model.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class BusinessPicPostRes {
    private long businessId;
    private List<String> pics;
}
