package com.green.jobdone.business.pic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessOnePicsGetRes {
    private long businessId;
    private List<BusinessOnePicDto> busiOnePics;
}
