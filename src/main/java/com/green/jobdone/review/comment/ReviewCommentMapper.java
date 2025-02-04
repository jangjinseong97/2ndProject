package com.green.jobdone.review.comment;

import com.green.jobdone.review.comment.model.ReviewCommentDelReq;
import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import com.green.jobdone.review.comment.model.ReviewCommentPostReq;
import com.green.jobdone.review.comment.model.ReviewCommentUpdReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {
    int insReviewComment(ReviewCommentPostReq p);
    ReviewCommentGetRes selReviewCommentByReviewId(long reviewId);
    int updReviewComment(ReviewCommentUpdReq p);
    int delReviewComment(ReviewCommentDelReq p);
}
