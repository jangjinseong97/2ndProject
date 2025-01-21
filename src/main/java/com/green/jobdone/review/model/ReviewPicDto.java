package com.green.jobdone.review.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewPicDto {
    private long reviewId;
    private List<String> pics;
}
