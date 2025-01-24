package com.green.jobdone.service.model;

import com.green.jobdone.service.model.Dto.ServicePutDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicePutReq {
    private long serviceId;
    private long userId;
    private int price;
    private String addComment;
    private int completed;
    private String startDate;
    private String endDate;
    private String mStartTime;
    private String mEndTime;
    private List<ServicePutDto> options;
}
