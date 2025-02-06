package com.green.jobdone.like.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostReq {

    @JsonIgnore
    private long userId;

    @Schema(description = "비즈니스 ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
}
