package com.green.jobdone.serviceType;

import com.green.jobdone.serviceType.detail.model.DetailTypeGetReq;
import com.green.jobdone.serviceType.detail.model.DetailTypeGetRes;
import com.green.jobdone.serviceType.detail.model.DetailTypePostReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetRes;
import com.green.jobdone.serviceType.model.ServiceTypePostReq;
import com.green.jobdone.common.model.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Tag(name = "카테고리 관리")
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    @PostMapping
    public ResultResponse<Integer> postCategory(@RequestBody ServiceTypePostReq p) {
        try {
            return ResultResponse.<Integer>builder()
                    .resultMessage("카테고리 등록 완료")
                    .resultData(serviceTypeService.postCategory(p))
                    .build();
        } catch (IllegalArgumentException e) {
            return  ResultResponse.<Integer>builder()
                    .resultMessage(e.getMessage())
                    .build();
        }
    }

    @GetMapping
    public ResultResponse<List<ServiceTypeGetRes>> getAllCategory(ServiceTypeGetReq p) {
        return ResultResponse.<List<ServiceTypeGetRes>>builder().resultMessage("카테고리 조회 완료")
                .resultData(serviceTypeService.getCategory(p))
                .build();
    }

    @PostMapping("/detail")
    public ResultResponse<Integer> postDetailType(@RequestBody DetailTypePostReq p){
        try {
            return ResultResponse.<Integer>builder()
                    .resultMessage("상세 서비스 등록 완료")
                    .resultData(serviceTypeService.postDetailType(p))
                    .build();
        }catch (IllegalArgumentException e) {
            return ResultResponse.<Integer>builder()
                    .resultMessage(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/detail")
    public ResultResponse<List<DetailTypeGetRes>> getAllDetailType(DetailTypeGetReq p){
        return ResultResponse.<List<DetailTypeGetRes>>builder().resultMessage("상세 서비스 조회 완료")
                .resultData(serviceTypeService.getDetailType(p)).build();
    }

}




