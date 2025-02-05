package com.green.jobdone.business;

import com.green.jobdone.business.model.*;
import com.green.jobdone.business.model.get.BusinessGetOneReq;
import com.green.jobdone.business.model.get.BusinessGetOneRes;
import com.green.jobdone.business.model.get.BusinessGetReq;
import com.green.jobdone.business.model.get.BusinessGetRes;
import com.green.jobdone.business.phone.BusinessPhonePostReq;
import com.green.jobdone.business.pic.BusinessOnePicsGetReq;
import com.green.jobdone.business.pic.BusinessOnePicsGetRes;
import com.green.jobdone.business.pic.BusinessPicDto;
import com.green.jobdone.business.pic.BusinessPicPostRes;
import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.common.PicUrlMaker;
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
    public int insBusiness(MultipartFile paper,MultipartFile logo, BusinessPostSignUpReq p) {

        // 사업자 등록번호 유효성 체크
        if (p.getBusinessNum() == null || p.getBusinessNum().isBlank()) {
            throw new IllegalArgumentException("사업자 등록번호가 유효하지 않습니다.");
        }
        //사업자 등록번호 중복체크
        int exists = businessMapper.existBusinessNum(p.getBusinessNum());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 등록된 사업자 번호입니다");
        }


        String paperPath = String.format("business/%d/paper",p.getBusinessId());
        myFileUtils.makeFolders(paperPath);
        String savedPicName = (paper != null ? myFileUtils.makeRandomFileName(paper) : null);
        String filePath = String.format("%s/%s",paperPath,savedPicName);
        try {
            myFileUtils.transferTo(paper,filePath);
        }catch (IOException e){
            log.error(e.getMessage());
            return 0;
        }
        String logoPath = String.format("business/%d/logo",p.getBusinessId());
        myFileUtils.makeFolders(logoPath);
        String savedPicName2 = (logo != null ? myFileUtils.makeRandomFileName(logo) : null);
        String filePath1 = String.format("%s/%s",logoPath,savedPicName2);
        try {
            myFileUtils.transferTo(logo,filePath1);
        }catch (IOException e){
            log.error(e.getMessage());
            return 0;
        }

        p.setPaper(savedPicName);
        p.setLogo(savedPicName2);
        return businessMapper.insBusiness(p);



    }

        /*
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
        }*/

    //사업상세정보 기입 - 로고사진은 따로 뺄게요 ~~


    public int udtBusiness(BusinessDetailPutReq p) {
        return businessMapper.udtBusiness(p);
    }



    // 로고 수정
    public int patchBusinessLogo(BusinessLogoPatchReq p, MultipartFile logo) {
        // 누락 파일 처리
        if (logo == null || logo.isEmpty()) {
            return 0;
        }

        // 로고파일 저장 폴더 경로
        String folderPath = String.format("business/%d/logo", p.getBusinessId());

        // 기존 로고 폴더가 있다면 폴더 삭제
        myFileUtils.deleteFolder(folderPath, true); // true: 폴더 내 모든 파일 및 하위 폴더 삭제

        // 새 폴더 생성
        myFileUtils.makeFolders(folderPath);

        // 랜덤 파일명 생성
        String savedPicName = myFileUtils.makeRandomFileName(logo); // 사진 등록 후 파일명 만들기
        String newFilePath = String.format("%s/%s", folderPath, savedPicName); // 만약 폴더가 없으면 새로 만들기

        try {
            // 파일을 지정된 경로로 저장
            myFileUtils.transferTo(logo, newFilePath);
            log.info("File successfully saved to: {}", newFilePath);
        } catch (IOException e) {
            // 파일 저장 실패시 처리
            log.error("Error saving file: {}", e.getMessage());
            return 0;
        }

        // DB에 로고 수정 정보 업데이트
        p.setLogo(savedPicName);
        return businessMapper.udtBusinessLogo(p);
    }
    // 사업자등록증 수정
    public int patchBusinessPaper(BusinessPaperPatchReq p, MultipartFile paper) {
        // 누락 파일 처리
        if (paper == null || paper.isEmpty()) {
            return 0;
        }

        // 로고파일 저장 폴더 경로
        String folderPath = String.format("business/%d/paper", p.getBusinessId());

        // 기존 로고 폴더가 있다면 폴더 삭제
        myFileUtils.deleteFolder(folderPath, true); // true: 폴더 내 모든 파일 및 하위 폴더 삭제

        // 새 폴더 생성
        myFileUtils.makeFolders(folderPath);

        // 랜덤 파일명 생성
        String savedPicName = myFileUtils.makeRandomFileName(paper); // 사진 등록 후 파일명 만들기
        String newFilePath = String.format("%s/%s", folderPath, savedPicName); // 만약 폴더가 없으면 새로 만들기

        try {
            // 파일을 지정된 경로로 저장
            myFileUtils.transferTo(paper, newFilePath);
            log.info("File successfully saved to: {}", newFilePath);
        } catch (IOException e) {
            // 파일 저장 실패시 처리
            log.error("Error saving file: {}", e.getMessage());
            return 0;
        }

        // DB에 로고 수정 정보 업데이트
        p.setPaper(savedPicName);
        return businessMapper.udtBusinessPaper(p);
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

        String middlePath = String.format("business/%d/pics", businessId);
        myFileUtils.makeFolders(middlePath);

        List<String> businessPicList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics) {
            //pics리스트에 있는 사진들 전수조사
            String savedPicName = myFileUtils.makeRandomFileName(pic);

            businessPicList.add(savedPicName);
            String filePath = String.format("%s/%s", middlePath, savedPicName);
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

        return BusinessPicPostRes.builder().businessId(businessId).pics(businessPicList).build();
    }

    public int udtBusinessPics(long businessPicId) {
        return businessMapper.putBusinessPic(businessPicId);
    }

    public int udtBusinessState(BusinessStatePutReq p) {
        return businessMapper.putBusinessState(p);
    }


    //업체 조회하기
    // 1. 업체 리스트 조회
    public List<BusinessGetRes> getBusinessList(BusinessGetReq p) {
        // 업체 리스트 조회
        List<BusinessGetRes> res = businessMapper.selAllBusiness(p);

        // 각 비즈니스 객체마다 사진 경로 생성
        for (BusinessGetRes business : res) {
            // 비즈니스 객체의 pic 필드를 이용하여 사진 경로 생성
            String picUrl = PicUrlMaker.makePicUrlBusiness(business.getBusinessId(), business.getPic());
            business.setPic(picUrl);  // 사진 경로 업데이트
        }

        return res;
    }

    // 2. 단일업체 조회
    public BusinessGetOneRes getBusinessOne(BusinessGetOneReq p) {
        BusinessGetOneRes res = businessMapper.selOneBusiness(p.getBusinessId());
        if (res == null) {
            res = new BusinessGetOneRes();  // res가 null일 경우 새로운 객체 생성
        }

        if (p.getBusinessId() > 0) {
            res.setBusinessId(p.getBusinessId());
        }

        if (res != null && res.getLogo() != null) {
            res.setLogo(PicUrlMaker.makePicUrlLogo(p.getBusinessId(),res.getLogo()));
        }
        return res;
    }

    //업체 하나에 있는 사진들
    public List<BusinessOnePicsGetRes> getBusinessOnePics(BusinessOnePicsGetReq p) {
        return businessMapper.getBusinessPicList(p);
    }


}




















