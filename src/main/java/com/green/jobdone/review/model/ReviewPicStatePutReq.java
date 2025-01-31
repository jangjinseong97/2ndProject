package com.green.jobdone.review.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewPicStatePutReq {
    @Schema(title = "리뷰 사진 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long reviewPicId;
}
