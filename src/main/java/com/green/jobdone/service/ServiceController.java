package com.green.jobdone.service;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.service.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.lang.model.element.Name;
import java.util.List;


@RequestMapping("service")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "서비스")
public class ServiceController {
    private final ServiceService serviceService;
    @Operation(summary = "예약하기")
    @PostMapping
    public ResultResponse<Integer> postService(@RequestBody ServicePostReq p ){
        int res = serviceService.postService(p);
        return ResultResponse.<Integer>builder()
                .resultData(res)
                .resultMessage(res == 0 ? "예약 실패" : "예약 성공")
                .build();
    }
    @Operation(summary = "서비스 현황 조회")
    @GetMapping
    public ResultResponse<List<ServiceGetRes>> getService(@ParameterObject @ModelAttribute ServiceGetReq p){
        List<ServiceGetRes> res = serviceService.getService(p);
        return ResultResponse.<List<ServiceGetRes>>builder()
                .resultMessage("조회 완료")
                .resultData(res)
                .build();
    }
    @Operation(summary = "견적서, 상세보기")
    @GetMapping("{serviceId}")
    public ResultResponse<ServiceGetOneRes> getOneService(@ParameterObject @ModelAttribute ServiceGetOneReq p){
        ServiceGetOneRes res = serviceService.getOneService(p);
        return ResultResponse.<ServiceGetOneRes>builder()
                .resultData(res)
                .resultMessage("조회 완료")
                .build();
    }

}
