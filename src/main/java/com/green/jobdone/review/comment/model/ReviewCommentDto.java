package com.green.jobdone.review.comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "리뷰 댓글 상세")
@EqualsAndHashCode
public class ReviewCommentDto {
    @JsonIgnore
    private long reviewId;
    @Schema(title = "리뷰 댓글 PK")
    private long commentId;
    @Schema(title = "리뷰 댓글 내용")
    private String contents;
    @Schema(title = "리뷰 댓글 작성자 유저 PK")
    private long userId;
    @Schema(title = "리뷰 댓글 작성자 유저 이름")
    private String name;
    @Schema(title = "리뷰 댓글 작성자 유저 프로필 사진 파일명")
    private String writerPic;
}
