package com.green.jobdone.business;

import com.green.jobdone.business.model.post.BusinessPhonePostReq;
import com.green.jobdone.business.model.post.BusinessPicDto;
import com.green.jobdone.business.model.post.BusinessPicPostRes;
import com.green.jobdone.business.model.post.BusinessPostSignUpReq;
import com.green.jobdone.business.model.udt.BusinessDetailPutReq;
import com.green.jobdone.common.MyFileUtils;
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
public class BusinessService {

    private final BusinessMapper businessMapper;
    private final MyFileUtils myFileUtils;

    //일단 사업등록하기 한번기입하면 수정불가하는 절대적정보
    public int insBusiness(MultipartFile paper, BusinessPostSignUpReq p){
        // 페이퍼는 사업자등록증 사진
        String fileName = paper != null? myFileUtils.makeRandomFileName(paper) : null;
        if (fileName == null) {
            return 0;
        }

        p.setPaper(fileName);
        long businessId = p.getBusinessId();
        String folderPath = String.format("business/%d", businessId);
        myFileUtils.makeFolders(folderPath);
        String filePath = String.format("%s/document/%s", folderPath, folderPath);
        try{
            myFileUtils.transferTo(paper,filePath);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return businessMapper.insBusiness(p);

    }

    //사업상세정보 기입
    public int udtBusiness(MultipartFile logo, BusinessDetailPutReq p){
        if(logo == null){
            return businessMapper.udtBusiness(p);
        }

        long businessId = p.getBusinessId();
        String folderPath = String.format("business/%d", businessId);
        myFileUtils.makeFolders(folderPath);
        String filePath = String.format("%s/logo/%s", folderPath, folderPath);
        try {
            myFileUtils.transferTo(logo,filePath);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return businessMapper.udtBusiness(p);
    }

    public int insBusinessPhone(BusinessPhonePostReq p){
        int exists = businessMapper.existBusinessPhone(p.getBusinessId(), p.getPhone());
        if (exists > 0){
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다");
        }
        return businessMapper.insBusinessPhone(p);
    }


    @Transactional
    public BusinessPicPostRes insBusinessPic(List<MultipartFile> pics, long businessId){

        String middlePath = String.format("business/%d", businessId);
        myFileUtils.makeFolders(middlePath);

        List<String> businessPicList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics){
            //pics리스트에 있는 사진들 전수조사
            String savedPicName = myFileUtils.makeRandomFileName(pic);

            businessPicList.add(savedPicName);
            String filePath = String.format("%s/pics/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic,filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BusinessPicDto businessPicDto = new BusinessPicDto();
        businessPicDto.setBusinessId(businessId);
        businessPicDto.setPics(businessPicList);
        int resultPics = businessMapper.insBusinessPic(businessPicDto);

        return BusinessPicPostRes.builder()
                .businessId(businessId)
                .pics(businessPicList)
                .build();
    }

}




















