package com.green.jobdone.business;

import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.pic.BusinessPicPostRes;
import com.green.jobdone.business.model.BusinessPostSignUpReq;
import com.green.jobdone.business.model.BusinessDetailPutReq;
import com.green.jobdone.common.model.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RequestMapping("business")
@RestController
@Slf4j
public class BusinessController {
    private final BusinessService businessService;

    @PostMapping("sign-up")
    @Operation(summary = "업체 등록")
    public ResultResponse<Integer> postBusiness(@RequestPart(required = true) MultipartFile paper,
                                                  @RequestPart BusinessPostSignUpReq p){
        int result = businessService.insBusiness(paper, p);

        return ResultResponse.<Integer>builder()
                .resultMessage("업체 등록 완료")
                .resultData(result)
                .build();
    }

    @PutMapping
    @Operation(summary = "업체 상세정보 기입")
    public ResultResponse<Integer> udtBusinessDetail(@RequestPart MultipartFile logo,
                                                     BusinessDetailPutReq p){
        int result = businessService.udtBusiness(logo, p);
        return ResultResponse.<Integer>builder()
                .resultData(result)
                .resultMessage(result==0?"업체 정보 수정 실패":"업체 정보 수정 성공")
                .build();
    }

    @PostMapping("phone")
    @Operation(summary = "업체 전화번호 기입")
    public ResultResponse<Integer> postBusinessPhone(BusinessPhonePostReq p){
        int result = businessService.insBusinessPhone(p);
        return ResultResponse.<Integer>builder()
                .resultMessage("전화번호 추가 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("businessPic")
    @Operation(summary = "업체 사진 등록")
    public ResultResponse<BusinessPicPostRes> postBusinessPic(@RequestPart List<MultipartFile> pics,
                                                              @RequestPart long businessId){
        BusinessPicPostRes res = businessService.insBusinessPic(pics, businessId);
        return ResultResponse.<BusinessPicPostRes>builder()
                .resultMessage("업체사진등록 완료")
                .resultData(res)
                .build();
    }

    @PutMapping
    @Operation(summary = "사진 유형 수정")
    public ResultResponse<Integer> putBusinessPic(long businessPicId){
        int res = businessService.putBusinessPics(businessPicId);

        return ResultResponse.<Integer>builder()
                .resultMessage("업체 사진 수정 완료")
                .resultData(res)
                .build();
    }
}














