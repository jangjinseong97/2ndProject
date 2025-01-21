package com.green.jobdone.like;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.like.model.LikeReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "찜")
public class LikeController {
    private final LikeService service;


    @PostMapping
    public ResultResponse<Integer> postLikeInfo(@RequestBody LikeReq p){

        int result=service.postLikeInfo(p);

        return ResultResponse.<Integer>builder()
                .resultMessage(result==1?"찜 등록되었습니다":"찜 등록 해제되었습니다")
                .resultData(result)
                .build();

    }
}
