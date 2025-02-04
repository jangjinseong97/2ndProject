package com.green.jobdone.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionPostDto {
    @Schema(title = "상품 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long productId;
    @Schema(title = "옵션 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long optionId;
    @Schema(title = "해당 업체 유저 PK, 인증된 사용자인지 판단하기 위해 필요함", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
}
