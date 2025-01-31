package com.green.jobdone.portfolio.model.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioListGetReq {
    @Schema(title = "대분류")
    private Long categoryId;
    @Schema(title = "소분류")
    private Long detailTypeId;
    @Schema(title = "업체")
    private Long businessId;


}
