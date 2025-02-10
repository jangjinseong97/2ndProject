package com.green.jobdone.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PortfolioPicReq {

    private long portfolioPicId;
    private long portfolioId;
}
