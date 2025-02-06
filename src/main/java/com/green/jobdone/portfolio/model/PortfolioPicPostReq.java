package com.green.jobdone.portfolio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PortfolioPicPostReq {
    @Schema(title = "businessId")
    private long businessId;
    @Schema(title = "portfolioId")
    private long portfolioId;
    public PortfolioPicPostReq(long businessId, long portfolioId) {
        this.businessId = businessId;
        this.portfolioId = portfolioId;
    }
}
