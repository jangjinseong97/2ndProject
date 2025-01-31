package com.green.jobdone.review.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewPutRes {
    private long reviewId;
    private List<String> pics;
}
