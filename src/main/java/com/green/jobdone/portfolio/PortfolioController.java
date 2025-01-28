package com.green.jobdone.portfolio;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.portfolio.model.PortfolioPicDto;
import com.green.jobdone.portfolio.model.PortfolioPicPostRes;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("portfolio")
@RestController
@Slf4j
@Tag(name = "포트폴리오")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("post")
    public ResultResponse<Integer> insPortfolio(PortfolioPostReq p) {
        int res = portfolioService.insPortfolio(p);
        return ResultResponse.<Integer>builder()
                .resultData(res)
                .resultMessage("포트폴리오 등록 완료")
                .build();
    }

    @PostMapping("portfolioPic")
    @Operation(summary = "포폴사진등록")
    public ResultResponse<PortfolioPicPostRes> postPortfolioPic(@RequestPart List<MultipartFile> pics,
                                                                @RequestPart long portfolioId) {
        PortfolioPicPostRes res = portfolioService.insPortfolioPic(pics, portfolioId);
        return ResultResponse.<PortfolioPicPostRes>builder()
                .resultMessage(res != null? "포트폴리오 사진 등록":"빠꾸먹음")
                .resultData(res)
                .build();
    }


}
