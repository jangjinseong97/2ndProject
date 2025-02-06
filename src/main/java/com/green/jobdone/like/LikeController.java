package com.green.jobdone.like;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.like.model.LikeGetRes;
import com.green.jobdone.like.model.LikePostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "찜")
public class LikeController {
    private final LikeService service;


    @PostMapping
    @Operation(summary = "찜 등록")
    public ResultResponse<Integer> postLikeInfo(@RequestBody LikePostReq p){


        int result=service.postLikeInfo(p);

        return ResultResponse.<Integer>builder()
                .resultMessage(result==1?"찜 등록되었습니다":"찜 등록 해제되었습니다")
                .resultData(result)
                .build();

    }


    @GetMapping
    @Operation(summary = "찜 등록 업체 확인")
    public ResultResponse<List<LikeGetRes>> getLikeInfo(){

        List<LikeGetRes> result=service.getLikeInfo();



        return ResultResponse.<List<LikeGetRes>>builder()
                .resultMessage(result==null&& result.isEmpty()?"찜한 업체 목록이 없습니다":"찜한 업체 목록 출력 완료")
                .resultData(result)
                .build();

    }
}
