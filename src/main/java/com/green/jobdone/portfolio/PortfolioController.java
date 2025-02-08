package com.green.jobdone.portfolio;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.portfolio.model.*;
import com.green.jobdone.portfolio.model.get.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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
                                                                @RequestPart PortfolioPicPostReq p) {
        PortfolioPicPostRes res = portfolioService.insPortfolioPic(pics,p.getBusinessId(),p.getPortfolioId());
        return ResultResponse.<PortfolioPicPostRes>builder()
                .resultMessage(res != null? "포트폴리오 사진 등록":"빠꾸먹음")
                .resultData(res)
                .build();
    }

    @DeleteMapping("portfolioPic")
    @Operation(summary = "포폴 사진 삭제")
    public ResultResponse<Integer> delPortfolioPic(@Valid @ParameterObject @ModelAttribute PortfolioPicDelReq p) {
        int res = portfolioService.delPortfolioPic(p);
        return ResultResponse.<Integer>builder().resultData(res).resultMessage("포폴 사진 삭제 완료").build();
    }

    @DeleteMapping("{portfolioId}")
    @Operation(summary = "포폴 삭제")
    public ResultResponse<Integer> delPortfolio(@Valid @ParameterObject @ModelAttribute PortfolioDelReq p) {
        int res = portfolioService.delPortfolio(p);
        return ResultResponse.<Integer>builder().resultData(res).resultMessage("포폴 삭제 완").build();
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
    public ResultResponse<PortfolioGetOneRes> selPortfolio(PortfolioGetOneReq p) {
        PortfolioGetOneReq req = new PortfolioGetOneReq(p.getPortfolioId());
        PortfolioGetOneRes res = portfolioService.getOnePortfolio(req);

        return ResultResponse.<PortfolioGetOneRes>builder().resultData(res).resultMessage("한 포폴 조회 완").build();
    }

    @GetMapping("pic/{portfolioId}")
    @Operation(summary = "한 포폴에서 사진 여러장 조회")
    public ResultResponse<List<PortfolioPicGetRes>> getPortfolioPicList(PortfolioGetOneReq p){
        PortfolioPicGetReq req = new PortfolioPicGetReq(p.getPortfolioId());
        List<PortfolioPicGetRes> res = portfolioService.getPortfolioPicList(req);

        return ResultResponse.<List<PortfolioPicGetRes>>builder().resultData(res).resultMessage(res != null? "포폴 사진 조회 완" : "포폴 사진 조회 싯빠이").build();
    }

}
