package com.green.jobdone.serviceType.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServiceTypePostReq {
    @JsonIgnore
    private long serviceTypeId;
    @Schema(title = "카테고리 이름", example = "청소", requiredMode = Schema.RequiredMode.REQUIRED)
    private String serviceTypeName;
}
