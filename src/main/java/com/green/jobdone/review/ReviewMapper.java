package com.green.jobdone.review;

import com.green.jobdone.review.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insReview(ReviewPostReq p);
    List<ReviewAndPicDto> selReviewWithPicList(ReviewGetReq p);
    int updReview(ReviewPutReq p);
    Long selUserIdByReviewId(long reviewId);
    int delReview(ReviewDelReq p);
    Long selUserIdByServiceId(long serviceId);

}
