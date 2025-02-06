package com.green.jobdone.review.comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentDelReq {
    @JsonIgnore
    private long userId;

    @Schema(title="리뷰 PK", description = "리뷰 PK", name="reviewId", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long reviewId;
}
