package com.green.jobdone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.jobdone.service.model.Dto.ServicePutDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private String startDate;
    private String endDate;
    private String mStartTime;
    private String mEndTime;
    private Integer pyeong;
    private List<ServicePutDto> options;
}
