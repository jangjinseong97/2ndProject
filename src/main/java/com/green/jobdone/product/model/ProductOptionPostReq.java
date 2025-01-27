package com.green.jobdone.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionPostReq {
    @Schema(title = "업체 상품 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long productId;
    @Schema(title = "상품 옵션명", example = "방갯수", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonIgnore
    private long productOptionId;
}
