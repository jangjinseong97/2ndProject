package com.green.jobdone.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPostReq {
    @Schema(description = "업체 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
    @JsonIgnore
    private long userId;
    @Schema(description = "디테일 타입 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long detailTypeId;
    @Schema(description = "업체 상품 시작 가격", example = "5000", requiredMode = Schema.RequiredMode.REQUIRED)
    private int productPrice;


    @JsonIgnore
    private long productId;

}
