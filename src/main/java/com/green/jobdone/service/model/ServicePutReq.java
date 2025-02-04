package com.green.jobdone.service.model;

import com.green.jobdone.service.model.Dto.ServiceEtcDto;
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
    private long providerUserId;
    private int totalPrice;
    private String addComment;
    @Schema(example = "2025/02/12")
    private String startDate;
    @Schema(example = "2025/02/12")
    private String endDate;
    @Schema(example = "11:00:00")
    private String mStartTime;
    @Schema(example = "18:00:00")
    private String mEndTime;
    private Integer pyeong;
    @JsonIgnore
    private List<ServicePutDto> options;
    private List<ServiceEtcDto> etc;
}
