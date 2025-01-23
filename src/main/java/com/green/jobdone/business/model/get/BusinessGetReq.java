package com.green.jobdone.business.model.get;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessGetReq {
    @Schema(name = "대분류")
    private long categoryId;
    @Schema(name = "소분류")
    private long detailTypeId;

}
