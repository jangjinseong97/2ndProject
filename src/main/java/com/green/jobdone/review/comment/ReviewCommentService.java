package com.green.jobdone.review.comment;

import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.review.comment.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public int updReviewComment(ReviewCommentUpdReq p) {
        p.setUserId(authenticationFacade.getSignedUserId());
        return reviewCommentMapper.updReviewComment(p);
    }

    public int delReviewComment(ReviewCommentDelReq p) {
        p.setUserId(authenticationFacade.getSignedUserId());
        return reviewCommentMapper.delReviewComment(p);
    }
}
