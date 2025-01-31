package com.green.jobdone.review;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.review.comment.ReviewCommentMapper;
import com.green.jobdone.review.comment.model.ReviewCommentDto;
import com.green.jobdone.review.comment.model.ReviewCommentGetRes;
import com.green.jobdone.review.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewPicMapper reviewPicMapper;
    private final ReviewCommentMapper reviewCommentMapper;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;

    @Transactional
    public ReviewPostRes postReview (List<MultipartFile> pics, ReviewPostReq p) {
        int result = reviewMapper.insReview(p);

        long reviewId = p.getReviewId();

        String middlePath = String.format("review/%d", reviewId);
        myFileUtils.makeFolders(middlePath);

        List<String> picNameList = new ArrayList<>(pics.size());
        for(MultipartFile pic : pics) {
            //각 파일 랜덤파일명 만들기
            String savedPicName = myFileUtils.makeRandomFileName(pic);
            picNameList.add(savedPicName);
            String filePath = String.format("%s/%s", middlePath,savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                //폴더삭제 처리
                String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                myFileUtils.deleteFolder(delFolderPath, true);
                throw new RuntimeException(e);
            }
        }
        ReviewPicDto reviewPicDto = new ReviewPicDto();
        reviewPicDto.setReviewId(reviewId);
        reviewPicDto.setPics(picNameList);
        int resultPics = reviewPicMapper.insReviewPic(reviewPicDto);

        return ReviewPostRes.builder()
                .reviewId(reviewId)
                .pics(picNameList)
                .build();
    }

    public List<ReviewGetRes> getFeedList(ReviewGetReq p) {
        List<ReviewGetRes> list = new ArrayList<>(p.getSize());

        //SELECT (1): review + review_pic
        if(p.getBusinessId() == null) {
            p.setUserId(authenticationFacade.getSignedUserId());
        }
        List<ReviewAndPicDto> reviewAndPicDtoList = reviewMapper.selReviewWithPicList(p);

        ReviewGetRes beforeReviewGetRes = new ReviewGetRes();
        for(ReviewAndPicDto reviewAndPicDto : reviewAndPicDtoList) {
            if(beforeReviewGetRes.getReviewId() != reviewAndPicDto.getReviewId()) {
                beforeReviewGetRes = new ReviewGetRes();
                beforeReviewGetRes.setPics(new ArrayList<>(3));
                list.add(beforeReviewGetRes);
                beforeReviewGetRes.setReviewId(reviewAndPicDto.getReviewId());
                beforeReviewGetRes.setContents(reviewAndPicDto.getContents());
                beforeReviewGetRes.setScore(reviewAndPicDto.getScore());
                beforeReviewGetRes.setCreatedAt(reviewAndPicDto.getCreatedAt());
                beforeReviewGetRes.setUserId(reviewAndPicDto.getUserId());
                beforeReviewGetRes.setName(reviewAndPicDto.getName());
                beforeReviewGetRes.setWriterPic(reviewAndPicDto.getWriterPic());
                beforeReviewGetRes.setDetailTypeName(reviewAndPicDto.getDetailTypeName());
            }
            beforeReviewGetRes.getPics().add(reviewAndPicDto.getPic());
            beforeReviewGetRes.getPics().add(reviewAndPicDto.getReviewPicId());
        }
        //SELECT (2): review_comment
        List<Long> reviewIds = new ArrayList<>(list.size());
        for(ReviewGetRes item : list) {
            ReviewCommentGetRes comment = reviewCommentMapper.selReviewCommentByReviewId(item.getReviewId());
            item.setComment(comment);
        }

        return list;
    }

    public ReviewPutRes updReview(List<MultipartFile> pics, ReviewPutReq p) {
        int result = reviewMapper.updReview(p);

        long reviewId = p.getReviewId();

        String middlePath = String.format("review/%d", reviewId);
        myFileUtils.makeFolders(middlePath);
        List<String> picNameList = new ArrayList<>(pics.size());
        for(MultipartFile pic : pics) {
            //각 파일 랜덤파일명 만들기
            String savedPicName = myFileUtils.makeRandomFileName(pic);
            picNameList.add(savedPicName);
            String filePath = String.format("%s/%s", middlePath,savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                //폴더삭제 처리
                String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                myFileUtils.deleteFolder(delFolderPath, true);
                throw new RuntimeException(e);
            }
        }
        ReviewPicDto reviewPicDto = new ReviewPicDto();
        reviewPicDto.setReviewId(reviewId);
        reviewPicDto.setPics(picNameList);
        int resultPics = reviewPicMapper.insReviewPic(reviewPicDto);

        int delPic = reviewPicMapper.delReviewPic(p.getReviewId());

        return ReviewPutRes.builder()
                .reviewId(reviewId)
                .pics(picNameList)
                .build();
    }

    public void updReviewPicState(ReviewPicStatePutReq p) {
        reviewPicMapper.updReviewPicState(p.getReviewPicId());
    }

}
