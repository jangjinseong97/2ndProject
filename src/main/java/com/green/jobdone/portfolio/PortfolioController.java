package com.green.jobdone.portfolio;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.portfolio.model.PortfolioPicPostRes;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
import com.green.jobdone.portfolio.model.PortfolioPutReq;
import com.green.jobdone.portfolio.model.get.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping
    @Operation(summary = "포폴 수정")
    public ResultResponse<Integer> udtPortfolioPut(PortfolioPutReq p) {
        int res = portfolioService.udtPortfolio(p);
        return ResultResponse.<Integer>builder().resultData(res).resultMessage(res > 0? "포폴 수정 완료": "포폴 수정 빠꾸").build();
    }

    @GetMapping
    @Operation(summary = "여러 포폴조회")
    public ResultResponse<List<PortfolioListGetRes>> getPortfolioList(PortfolioListGetReq p) {
        List<PortfolioListGetRes> res = portfolioService.getPortfolioList(p);
        return ResultResponse.<List<PortfolioListGetRes>>builder().resultData(res).resultMessage("포폴 리스트 조회 완료").build();
    }

    @GetMapping("/{portfolioId}")
    @Operation(summary = "한 포폴 조회")
    public ResultResponse<PortfolioGetOneRes> selPortfolio(@PathVariable Long portfolioId) {
        PortfolioGetOneReq req = new PortfolioGetOneReq(portfolioId);
        PortfolioGetOneRes res = portfolioService.getOnePortfolio(req);

        return ResultResponse.<PortfolioGetOneRes>builder().resultData(res).resultMessage("한 포폴 조회 완").build();
    }

    @GetMapping("pic/{portfolioId}")
    @Operation(summary = "한 포폴에서 사진 여러장 조회")
    public ResultResponse<List<PortfolioPicGetRes>> getPortfolioPicList(PortfolioPicGetReq p){
        List<PortfolioPicGetRes> res = portfolioService.getPortfolioPicList(p);

        return ResultResponse.<List<PortfolioPicGetRes>>builder().resultData(res).resultMessage(res != null? "포폴 사진 조회 완" : "포폴 사진 조회 싯빠이").build();
    }

}
