package com.green.jobdone.portfolio;

import com.green.jobdone.portfolio.model.PortfolioPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioMapper portfolioMapper;

    public int insPortfolio(PortfolioPostReq p){
        return portfolioMapper.insPortfolio(p);
    }
}
