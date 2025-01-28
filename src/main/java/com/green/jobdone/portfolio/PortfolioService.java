package com.green.jobdone.portfolio;

import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.pic.BusinessPicPostRes;
import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.portfolio.model.PortfolioPicDto;
import com.green.jobdone.portfolio.model.PortfolioPicPostRes;
import com.green.jobdone.portfolio.model.PortfolioPostReq;
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
    public PortfolioPicPostRes insPortfolioPic(List<MultipartFile> pics, long portfolioId) {

        String middlePath = String.format("portfolio/%d", portfolioId);
        myFileUtils.makeFolders(middlePath);

        List<String> portfolioPicList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics) {
            //pics리스트에 있는 사진들 전수조사
            String savedPicName = myFileUtils.makeRandomFileName(pic);

            portfolioPicList.add(savedPicName);
            String filePath = String.format("%s/pics/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
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











}
