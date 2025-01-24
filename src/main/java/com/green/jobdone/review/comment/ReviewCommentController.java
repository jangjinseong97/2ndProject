package com.green.jobdone.review.comment;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.review.comment.model.ReviewCommentGetReq;
import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import com.green.jobdone.review.comment.model.ReviewCommentPostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("review/comment")
@Tag(name = "리뷰 댓글", description = "리뷰 댓글 관리")
public class ReviewCommentController {
    private final ReviewCommentService reviewCommentService;

    @PostMapping
    public ResultResponse<Long> postReviewComment(@RequestBody ReviewCommentPostReq p) {
        long result = reviewCommentService.postReviewComment(p);
        return ResultResponse.<Long>builder()
                .resultMessage("댓글 등록 완료")
                .resultData(result)
                .build();
    }

    @GetMapping
    public ResultResponse<ReviewCommentGetRes> getFeedComment(@Valid @ModelAttribute @ParameterObject ReviewCommentGetReq p) {
        ReviewCommentGetRes res = reviewCommentService.selReviewCommentByReviewId(p);
        return ResultResponse.<ReviewCommentGetRes>builder()
                .resultMessage("댓글 검색 완료")
                .resultData(res)
                .build();
    }
}
