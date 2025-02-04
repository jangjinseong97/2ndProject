package com.green.jobdone.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionDetailPatchReq {
    @Schema(title = "상품옵션에 대한 상세옵션 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long optionDetailId;
    @Schema(description = "해당 업체 유저 PK, 인증된 사용자인지 판단하기 위해 필요", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "상품옵션에 대한 상세옵션에 대한 설명", example = "1개입니다")
    private String contents;
    @Schema(title = "상품옵션에 대한 상세옵션에 대한 가격", example = "5000")
    private int optionDetailPrice;
    @Schema(title = "상품옵션에 대한 상세옵션에 대한 이름", example = "1개")
    private String optionDetailName;
}
