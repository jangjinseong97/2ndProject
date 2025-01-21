package com.green.jobdone.review;

import com.green.jobdone.review.model.ReviewPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int insReview(ReviewPostReq p);

}
