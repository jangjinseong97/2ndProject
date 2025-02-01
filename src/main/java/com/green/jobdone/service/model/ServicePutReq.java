package com.green.jobdone.service.model;

import com.green.jobdone.service.model.Dto.ServiceEtcDto;
import com.green.jobdone.service.model.Dto.ServicePutDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicePutReq {
    private long serviceId;
    private long providerUserId;
    private int totalPrice;
    private String addComment;
    private String startDate;
    private String endDate;
    private String mStartTime;
    private String mEndTime;
    private Integer pyeong;
    private List<ServicePutDto> options;
    private List<ServiceEtcDto> etc;
}
