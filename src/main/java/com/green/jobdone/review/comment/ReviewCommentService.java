package com.green.jobdone.review.comment;

import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.review.comment.model.ReviewCommentGetReq;
import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import com.green.jobdone.review.comment.model.ReviewCommentPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCommentService {
    private final ReviewCommentMapper reviewCommentMapper;
    private final AuthenticationFacade authenticationFacade;

    public long postReviewComment(ReviewCommentPostReq p) {
        p.setUserId(authenticationFacade.getSignedUserId());
        reviewCommentMapper.insReviewComment(p);
        return p.getCommentId();
    }

    public ReviewCommentGetRes selReviewCommentByReviewId(ReviewCommentGetReq p) {
        return reviewCommentMapper.selReviewCommentByReviewId(p.getReviewId());
    }
}
