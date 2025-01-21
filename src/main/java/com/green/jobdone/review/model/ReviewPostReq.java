package com.green.jobdone.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "리뷰 등록 요청")
public class ReviewPostReq {
    @JsonIgnore
    private long reviewId;

    @Schema(title = "서비스 PK", example = "1")
    private long serviceId;

    @Schema(title = "리뷰 내용", example = "너무 깨끗하게 신경써주셨어요!!")
    private String contents;

    @Schema(title = "리뷰 별점", example = "4.3")
    private double score;
}
