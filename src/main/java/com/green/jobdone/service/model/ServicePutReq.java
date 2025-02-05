package com.green.jobdone.service.model;

import com.green.jobdone.service.model.Dto.ServiceEtcDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.jobdone.service.model.Dto.ServicePutDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicePutReq {
    @NotNull(message = "서비스 pk는 필수입니다.")
    private long serviceId;
    @JsonIgnore
    private long providerUserId;
    private int totalPrice;
    private String addComment;
    @Schema(example = "2025/02/12")
    private String startDate;
    @Schema(example = "2025/02/12")
    @NotNull(message = "끝나는 날짜는 필수로 작성해야 됩니다.")
    private String endDate;
    @Schema(example = "11:00:00")
    private String mStartTime;
    @Schema(example = "18:00:00")
    @NotNull(message = "끝나는 시간은 필수로 작성해야 됩니다.")
    private String mEndTime;
    private Integer pyeong;
    @JsonIgnore
    private List<ServicePutDto> options;
    private List<ServiceEtcDto> etc;
}
