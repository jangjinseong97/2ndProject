package com.green.jobdone.review.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewPostRes {
    private long reviewId;
    private List<String> pics;
}
