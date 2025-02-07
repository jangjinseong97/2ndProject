package com.green.jobdone.portfolio.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PortfolioDelReq {
    private long businessId;
    private long portfolioId;
}
