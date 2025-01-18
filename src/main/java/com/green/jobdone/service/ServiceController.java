package com.green.jobdone.service;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.service.model.ServicePostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.Name;

@RequestMapping("service")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "서비스")
public class ServiceController {
    private ServiceService serviceService;
    @PostMapping
    public ResultResponse<Integer> postService(@RequestBody ServicePostReq p ){
        int res = serviceService.postService(p);
        return ResultResponse.<Integer>builder()
                .resultData(res)
                .resultMessage(res == 0 ? "예약 실패" : "예약 성공")
                .build();
    }

}
