package com.green.jobdone.review.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewPutReq {
    @Schema(title = "리뷰 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long reviewId;
    @Schema(title = "완료된 서비스 pk", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long serviceId;
    @Schema(title = "변경할 리뷰 내용", example = "엄청 서비스가 친절했습니다!", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String contents;
    @Schema(title = "변경할 리뷰 별점", example = "4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double score;

}
