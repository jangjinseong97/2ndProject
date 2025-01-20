package com.green.jobdone.product.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionPostReq {
    @Schema(title = "상품 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long productId;
    @Schema(title = "상품명", example = "방개수", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonIgnore
    private long productOptionId;
}
