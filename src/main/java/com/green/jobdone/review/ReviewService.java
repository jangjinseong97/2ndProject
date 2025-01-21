package com.green.jobdone.review;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.review.model.ReviewPicDto;
import com.green.jobdone.review.model.ReviewPostReq;
import com.green.jobdone.review.model.ReviewPostRes;
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
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewPicMapper reviewPicMapper;
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
}
