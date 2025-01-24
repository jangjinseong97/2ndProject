package com.green.jobdone.business;

import com.green.jobdone.business.model.BusinessLogoPatchReq;
import com.green.jobdone.business.model.BusinessStatePutReq;
import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.pic.BusinessPicPostRes;
import com.green.jobdone.business.model.BusinessPostSignUpReq;
import com.green.jobdone.business.model.BusinessDetailPutReq;
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
    public int insBusiness(MultipartFile paper, BusinessPostSignUpReq p) {

        // 사업자 등록번호 유효성 체크
        if (p.getBusinessNum() == null || p.getBusinessNum().isBlank()) {
            throw new IllegalArgumentException("사업자 등록번호가 유효하지 않습니다.");
        }
        //사업자 등록번호 중복체크
        int exists = businessMapper.existBusinessNum(p.getBusinessNum());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 등록된 사업자 번호입니다");
        }

        // 페이퍼는 사업자등록증 사진
        String fileName = null;
        if (paper != null && !paper.isEmpty()) {
            fileName = myFileUtils.makeRandomFileName(paper);
            if (fileName == null) {
                throw new IllegalStateException("파일명을 생성할 수 없습니다.");
            }
        }

        p.setPaper(fileName);
        if (p.getBusinessId() <= 0) {
            throw new IllegalArgumentException("유효하지 않은 사업자 ID입니다.");
        }

        String folderPath = String.format("business/%d", p.getBusinessId());
        myFileUtils.makeFolders(folderPath);
        // 파일 경로 및 파일저장
        if (fileName != null) {
            String filePath = String.format("%s/document/%s", folderPath, folderPath);
            try {
                myFileUtils.transferTo(paper, filePath);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new IllegalArgumentException("파일 업로드 중 오류가 발생하였습니다.");
            }
        }

        //db에 사업자 정보 저장
        try {
            return businessMapper.insBusiness(p);
        } catch (Exception e) {
            log.error("사업자 정보 저장 중 오류 발생: {}", e.getMessage());
            throw new IllegalStateException("사업자 정보 저장 중 오류가 발생했습니다.");
        }
    }

    //사업상세정보 기입 - 로고사진은 따로 뺄게요 ~~

    public int udtBusiness(BusinessDetailPutReq p) {
        return businessMapper.udtBusiness(p);
    }
    // 로고 수정
    public int  patchBusinessLogo(BusinessLogoPatchReq p, MultipartFile logo) {

        if (logo == null || logo.isEmpty()) {
            int result = businessMapper.udtBusinessLogo(p);
            return result;
        }

        //저장할 파일명(랜덤한 파일명) 생성한다. 이때, 확장자는 오리지날 파일명과 일치하게 한다.
        String savedPicName = myFileUtils.makeRandomFileName(logo);

        p.setLogoName(savedPicName);

        int result = businessMapper.udtBusinessLogo(p);

        myFileUtils.deleteFile(savedPicName);

        String folderPath = String.format("business/%d", p.getBusinessId()); //만약 폴더가 없었으면 새로 만들기
        myFileUtils.makeFolders(folderPath);

        //기존 파일 삭제(방법 3가지 [1]: 폴더를 지운다. [2]select해서 기존 파일명을 얻어온다. [3]기존 파일명을 FE에서 받는다.)

        myFileUtils.deleteFolder(folderPath, false);

        //DB에 튜플을 수정(Update)한다.
        p.setLogoName(savedPicName);


        //원하는 위치에 저장할 파일명으로 파일을 이동(transferTo)한다.
        String filePath = String.format("business/%d/logo/%s", p.getBusinessId(), savedPicName);

        try {
            myFileUtils.transferTo(p.getLogo(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insBusinessPhone(BusinessPhonePostReq p) {
        int exists = businessMapper.existBusinessPhone(p.getBusinessId(), p.getPhone());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다");
        }
        return businessMapper.insBusinessPhone(p);
    }


    @Transactional
    public BusinessPicPostRes insBusinessPic(List<MultipartFile> pics, long businessId) {

        String middlePath = String.format("business/%d", businessId);
        myFileUtils.makeFolders(middlePath);

        List<String> businessPicList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics) {
            //pics리스트에 있는 사진들 전수조사
            String savedPicName = myFileUtils.makeRandomFileName(pic);

            businessPicList.add(savedPicName);
            String filePath = String.format("%s/pics/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
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

    public int udtBusinessPics(long businessPicId) {
        return businessMapper.putBusinessPic(businessPicId);
    }

    public int udtBusinessState(BusinessStatePutReq p) {
        return businessMapper.putBusinessState(p);
    }


}




















