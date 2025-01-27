package com.green.jobdone.product;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.product.model.ProductGetRes;
import com.green.jobdone.product.model.ProductOptionDetailPostReq;
import com.green.jobdone.product.model.ProductOptionPostReq;
import com.green.jobdone.product.model.ProductPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;

@RestController
@RequestMapping("product")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "상품")
public class ProductController {
    private final ProductService service;


    @PostMapping
    @Operation(summary = "업체 상품 등록")
    public ResultResponse<Integer> postProduct(@RequestBody ProductPostReq p) {


        int result = service.postProduct(p);


        return ResultResponse.<Integer>builder()
                .resultMessage(result==0?"이미 등록하셨습니다":"상품 등록 완료")
                .resultData(result)
                .build();

    }

    @PostMapping("option")
    @Operation(summary = "업체 상품 옵션 등록")

    public ResultResponse<Integer> postProductOption(@RequestBody ProductOptionPostReq p) {


        int result = service.postProductOption(p);


        return ResultResponse.<Integer>builder()
                .resultMessage(result==0?"이미 등록된 옵션입니다":"상품 옵션 등록 완료")
                .resultData(result)
                .build();

    }

    @PostMapping("option/detail")
    @Operation(summary = "업체 상품 옵션 상세 정보 등록")
    public ResultResponse<Integer> postOptionDetail(@RequestBody ProductOptionDetailPostReq p) {


        int result = service.postOptionDetail(p);


        return ResultResponse.<Integer>builder()
                .resultMessage(result==0?"이미 등록된 상세 옵션입니다":"상품 상세 옵션 등록 완료")
                .resultData(result)
                .build();

    }



    @GetMapping
    @Operation(summary = "업체의 상품,상품상세옵션 조회")
    public ResultResponse<List<ProductGetRes>> getProductInfoByBusiness(@RequestParam long businessId) {


        List<ProductGetRes> result = service.getProductInfoByBusiness(businessId);


        return ResultResponse.<List<ProductGetRes>>builder()
                .resultMessage(result!=null&&!result.isEmpty()?"업체의 상품,상품상세옵션 조회 완료":"등록된 상품,상품상세옵션이 없습니다")
                .resultData(result)
                .build();

    }

}
