package com.green.jobdone.review.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ReviewAndPicDto {
    private long reviewId;
    private String contents;
    private double score;
    private String createdAt;
    private long userId;
    private String name;
    private String writerPic;
    private String detailTypeName;
    private double averageScore;
    private String pic;
    private String reviewPicId;
}
