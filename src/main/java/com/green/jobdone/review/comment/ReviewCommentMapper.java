package com.green.jobdone.review.comment;

import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import com.green.jobdone.review.comment.model.ReviewCommentPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {
    int insReviewComment(ReviewCommentPostReq p);
    ReviewCommentGetRes selReviewCommentByReviewId(long reviewId);
}
