package com.green.jobdone.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BusinessStatePutReq {
    private long businessId;
    private int state;
}
