package com.green.jobdone.portfolio.model.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioGetOneRes {
    private long portfolioId;
    private String title;
    private String category;
    private String detailType;
    private int price;
    private String takingTime;
    private String contents;

}
