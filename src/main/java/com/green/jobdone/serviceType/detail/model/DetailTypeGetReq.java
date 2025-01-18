package com.green.jobdone.serviceType.detail.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTypeGetReq {
    @Schema(title = "상세 타입 아이디", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long serviceTypeId;
}
