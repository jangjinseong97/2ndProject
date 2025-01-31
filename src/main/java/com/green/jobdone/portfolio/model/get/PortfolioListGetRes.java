package com.green.jobdone.portfolio.model.get;

import com.green.jobdone.common.PicUrlMaker;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PortfolioListGetRes {
    private long portfolioId;
    private String title;
    private String isThumnail;

    public PortfolioListGetRes(long portfolioId, String title, String isThumnail) {
        this.portfolioId = portfolioId;
        this.title = title;
        this.isThumnail = PicUrlMaker.makePicUrl(portfolioId, isThumnail);
    }


}
