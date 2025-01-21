package com.green.jobdone.review;

import com.green.jobdone.review.model.ReviewPicDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewPicMapper {
    int insReviewPic(ReviewPicDto p);
}
