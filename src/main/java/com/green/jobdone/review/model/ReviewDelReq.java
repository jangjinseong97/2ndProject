package com.green.jobdone.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDelReq {
    @JsonIgnore
    private long userId;

    @Schema(title = "리뷰 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long reviewId;
}
