package com.green.jobdone.portfolio.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PortfolioPicPostRes {
    private long portfolioPicId;
    private List<String> pics;
}
