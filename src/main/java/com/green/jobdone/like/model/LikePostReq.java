package com.green.jobdone.like.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostReq {
    @Schema(description = "유저 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;

    @Schema(description = "비즈니스 ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
}
