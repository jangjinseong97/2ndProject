package com.green.jobdone.product;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.product.model.ProductPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;

@RestController
@RequestMapping("product")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "상품")
public class ProductController {
    private final ProductService service;


    @PostMapping
    @Operation(summary = "상품 등록")
    public ResultResponse<Integer> postProduct(@RequestBody ProductPostReq p) {


        int result = service.postProduct(p);


        return ResultResponse.<Integer>builder()
                .resultMessage(result==0?"이미 등록하셨습니다":"상품 등록 완료")
                .resultData(result)
                .build();


    }
}
