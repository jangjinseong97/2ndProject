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
    private int price;
}
