package com.green.jobdone.like;

import com.green.jobdone.like.model.LikeGetRes;
import com.green.jobdone.like.model.LikePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeMapper mapper;

    public int postLikeInfo(LikePostReq p){

            int result=mapper.deleteLikeInfo(p);

            if(result==1){
                return 0;
            }

            int result1=mapper.postLikeInfo(p);

            return 1;

    }


    public List<LikeGetRes> getLikeInfo(long userId){

        List<LikeGetRes> res=mapper.getLikeInfo(userId);


        return res;

    }
}
