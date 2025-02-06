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

        long userId=authenticationFacade.getSignedUserId();

        p.setUserId(userId);


        int result = mapper.deleteLikeInfo(p);

        if (result == 1) {
            return 0;
        }

        int result1 = mapper.postLikeInfo(p);

        return 1;

    }


    public List<LikeGetRes> getLikeInfo() {

        long userId=authenticationFacade.getSignedUserId();

        List<LikeGetRes> res = mapper.getLikeInfo(userId);

        for (LikeGetRes r : res) {
            r.setPic(PicUrlMaker.makePicUrlBusiness(r.getBusinessId(), r.getPic()));

            // 업체사진 끌어올거면 이거 써야함.

        }

        return res;

    }
}
