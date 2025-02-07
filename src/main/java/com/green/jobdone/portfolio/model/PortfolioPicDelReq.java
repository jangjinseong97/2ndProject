package com.green.jobdone.portfolio.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PortfolioPicDelReq {
    private long businessId;
    private long portfolioId;
    private long portfolioPicId;
}
