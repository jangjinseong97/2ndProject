package com.green.jobdone.portfolio.model.get;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioGetOneReq {
    @Schema(title = "portfolioId")
    private long portfolioId;
    public PortfolioGetOneReq(long portfolioId) {
        this.portfolioId = portfolioId;
    }

}
