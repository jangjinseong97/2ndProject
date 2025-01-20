package com.green.jobdone.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPostReq {
    @Schema(title = "업체 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
    @Schema(title = "디테일 타입 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long detailTypeId;

    @JsonIgnore
    private long productId;

}
