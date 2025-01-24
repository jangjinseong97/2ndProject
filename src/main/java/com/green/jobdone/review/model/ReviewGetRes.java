package com.green.jobdone.review.model;

import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Schema(title = "피드 정보")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewGetRes {
    @Schema(title = "리뷰 PK")
    private long reviewId;
    @Schema(title = "리뷰 내용")
    private String contents;
    @Schema(title = "리뷰 평점")
    private double score;
    @Schema(title = "리뷰 생성일시")
    private String createdAt;
    @Schema(title = "작성자 유저 PK")
    private long userId;
    @Schema(title = "작성자 유저 이름")
    private String name;
    @Schema(title = "작성자 유저 프로필 사진파일명")
    private String writerPic;
    @Schema(title = "소분류 이름 ex)사무실 청소")
    private String detailTypeName;

    @Schema(title = "리뷰 사진 리스트")
    private List<String> pics;
    @Schema(title = "리뷰 댓글")
    private ReviewCommentGetRes comment;

    public ReviewGetRes(ReviewWithPicDto dto) {
        this.reviewId = dto.getReviewId();
        this.contents = dto.getContents();
        this.score = dto.getScore();
        this.createdAt = dto.getCreatedAt();
        this.userId = dto.getUserId();
        this.name = dto.getName();
        this.writerPic = dto.getWriterPic();
        this.pics = dto.getPics();
        this.comment = dto.getComment();
    }
}
