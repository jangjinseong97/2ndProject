package com.green.jobdone.portfolio;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("portfolio")
@RestController
@Slf4j
@Tag(name = "포트폴리오")
public class PortfolioController {
    private final PortfolioService portfolioService;

    public ResultResponse<Integer> insPortfolio(PortfolioPostReq p) {
        int res = portfolioService.insPortfolio(p);
        return ResultResponse.<Integer>builder()
                .resultData(res)
                .resultMessage("포트폴리오 등록 완료")
                .build();
    }


}
