package com.green.jobdone.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPatchReq {
    @Schema(title = "업체 상품 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long productId;
    @Schema(title = "수정할 업체 상품 시작 가격", example = "40000", requiredMode = Schema.RequiredMode.REQUIRED)
    private int productPrice;
    @Schema(title = "해당 업체 유저 PK, 인증된 사용자인지 판단하기 위해 필요", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;

}
