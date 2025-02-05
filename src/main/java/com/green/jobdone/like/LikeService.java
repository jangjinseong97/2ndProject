package com.green.jobdone.like;

import com.green.jobdone.common.PicUrlMaker;
import com.green.jobdone.config.security.AuthenticationFacade;
import com.green.jobdone.like.model.LikeGetRes;
import com.green.jobdone.like.model.LikePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    public int postLikeInfo(LikePostReq p) {

//        if (p.getUserId() != authenticationFacade.getSignedUserId()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다.");
//        }   //나중에 최종적으로 주석 풀기

        int result = mapper.deleteLikeInfo(p);

        if (result == 1) {
            return 0;
        }

        int result1 = mapper.postLikeInfo(p);

        return 1;

    }


    public List<LikeGetRes> getLikeInfo(long userId) {

//        if (userId != authenticationFacade.getSignedUserId()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다.");
//        }   //나중에 최종적으로 주석 풀기

        List<LikeGetRes> res = mapper.getLikeInfo(userId);

        for (LikeGetRes r : res) {
            r.setPic(PicUrlMaker.makePicUrlBusiness(r.getBusinessId(), r.getPic()));
            // 업체사진 끌어올거면 이거 써야함.
        }

        return res;

    }
}
