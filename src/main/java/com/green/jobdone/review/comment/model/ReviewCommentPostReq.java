package com.green.jobdone.review.comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Schema(title = "리뷰 댓글 등록 요청")
@ToString
public class ReviewCommentPostReq {
    @JsonIgnore
    private long commentId;
    @Schema(title = "리뷰 PK", example = "6", requiredMode = Schema.RequiredMode.REQUIRED)
    private long reviewId;
    @JsonIgnore
    private long userId;
    @Schema(title = "댓글 내용", example = "댓글입니다.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;
}
