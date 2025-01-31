package com.green.jobdone.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PortfolioPicDto {
    @JsonIgnore
    private long portfolioPicId;
    private long portfolioId;
    private List<String> pics;
}
