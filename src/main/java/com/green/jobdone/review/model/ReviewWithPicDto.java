package com.green.jobdone.review.model;

import com.green.jobdone.review.comment.model.ReviewCommentDto;
import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReviewWithPicDto {
    private long reviewId;
    private String contents;
    private double score;
    private String createdAt;
    private long userId;
    private String name;
    private String writerPic;
    private String detailName;
    private List<String> pics;
    private ReviewCommentGetRes comment;
}


