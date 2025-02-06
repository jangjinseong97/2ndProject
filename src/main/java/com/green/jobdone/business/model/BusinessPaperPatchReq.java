package com.green.jobdone.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessPaperPatchReq {


    @Schema(title = "회사pk",requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
    @Schema(title = "회사pk",requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserId;

    @JsonIgnore
    private String paper;
}
