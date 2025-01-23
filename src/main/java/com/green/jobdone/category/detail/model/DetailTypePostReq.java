package com.green.jobdone.category.detail.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTypePostReq {
    @Schema(title = "카테고리 아이디", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long categoryId;
    @Schema(title = "디테일 카테고리 이름", example = "벌레 퇴치", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
