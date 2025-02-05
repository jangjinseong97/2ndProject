package com.green.jobdone.portfolio;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.common.PicUrlMaker;
import com.green.jobdone.portfolio.model.PortfolioPicDto;
import com.green.jobdone.portfolio.model.PortfolioPicPostRes;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
import com.green.jobdone.portfolio.model.PortfolioPutReq;
import com.green.jobdone.portfolio.model.get.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioMapper portfolioMapper;
    private final MyFileUtils myFileUtils;

    public int insPortfolio(PortfolioPostReq p){

        return portfolioMapper.insPortfolio(p);
    }

    @Transactional
    public PortfolioPicPostRes insPortfolioPic(List<MultipartFile> pics,long businessId, long portfolioId) {

        String middlePath = String.format("business/%d/portfolio/%d/pics", businessId, portfolioId);
        myFileUtils.makeFolders(middlePath);

        List<String> portfolioPicList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics) {
            //pics리스트에 있는 사진들 전수조사
            String savedPicName = myFileUtils.makeRandomFileName(pic);

            portfolioPicList.add(savedPicName);
            String filePath = String.format("%s/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath); // 포폴 사진값 설정해놓음
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PortfolioPicDto portfolioPicDto = new PortfolioPicDto();
        portfolioPicDto.setPortfolioId(portfolioId);
        portfolioPicDto.setPics(portfolioPicList);
        int resultPics = portfolioMapper.insPortfolioPic(portfolioPicDto);

        return PortfolioPicPostRes.builder().portfolioPicId(portfolioId).pics(portfolioPicList).build();
    }

    public int udtPortfolio(PortfolioPutReq p){
        return portfolioMapper.udtPortfolio(p);
    }

    //포폴리스트 조회
    public List<PortfolioListGetRes> getPortfolioList(PortfolioListGetReq p){
        List<PortfolioListGetRes> res = portfolioMapper.selAllPortfolioList(p);

        for (PortfolioListGetRes r : res) {
            String picUrl = PicUrlMaker.makePicUrlPortfolio(r.getBusinessId(),r.getPortfolioId(),r.getIsThumnail());
            r.setIsThumnail(picUrl);
        }

        return res;
    }

    public PortfolioGetOneRes getOnePortfolio(PortfolioGetOneReq p) {
        PortfolioGetOneRes res = portfolioMapper.selOnePortfolio(p.getPortfolioId());
        if (res == null) {
            res = new PortfolioGetOneRes();
        }

        if (p.getPortfolioId()>0){
            res.setPortfolioId(p.getPortfolioId());
        }
        return res;
    }

    public List<PortfolioPicGetRes> getPortfolioPicList(PortfolioPicGetReq p) {
        List<PortfolioPicGetRes> res = portfolioMapper.getPortfolioPicList(p);
        for (PortfolioPicGetRes pic : res) {
            String picUrl = PicUrlMaker.makePicUrlPortfolio(pic.getBusinessId(),pic.getPortfolioId(),pic.getPic());
            pic.setPic(picUrl);
        }
        return res;
    }











}
