package com.green.jobdone.portfolio;

import com.green.jobdone.portfolio.model.PortfolioPicDto;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortfolioMapper {
    int insPortfolio(PortfolioPostReq p);
    int insPortfolioPic(PortfolioPicDto p);
}
