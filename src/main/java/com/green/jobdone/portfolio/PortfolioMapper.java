package com.green.jobdone.portfolio;

import com.green.jobdone.portfolio.model.*;
import com.green.jobdone.portfolio.model.get.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortfolioMapper {
    int insPortfolio(PortfolioPostReq p);
    int delPortfolio(long portfolioId);

    int udtPortfolio(PortfolioPutReq p);

    int delPortfolio(PortfolioDelReq p);

    List<PortfolioListGetRes> selAllPortfolioList(PortfolioListGetReq p);
    PortfolioGetOneRes selOnePortfolio(long portfolioId);


    //pic
    int insPortfolioPic(PortfolioPicDto p);
    List<PortfolioPicGetRes> getPortfolioPicList(PortfolioPicGetReq p);
    int delPortfolioPic(PortfolioPicDelReq p);
    String getPortfolioPicName(long portfolioPicId);
}

