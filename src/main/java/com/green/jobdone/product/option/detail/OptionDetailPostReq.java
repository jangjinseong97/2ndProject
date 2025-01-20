package com.green.jobdone.product.option.detail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDetailPostReq {
    @Schema(title = "상품옵션 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long productOptionId;
    @Schema(title = "옵션 상세 명", example = "3개", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title = "옵션 내용", example = "3개방에서 벌레 퇴치", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;
    @Schema(title = "옵션별 가격", example = "40000", requiredMode = Schema.RequiredMode.REQUIRED)
    private int price;

    @JsonIgnore
    private long optionDetailId;
}
