package com.green.jobdone.like;

import com.green.jobdone.like.model.LikeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeMapper mapper;

    public int postLikeInfo(LikeReq p){

            int result=mapper.deleteLikeInfo(p);

            if(result==1){
                return 0;
            }

            int result1=mapper.postLikeInfo(p);

            return 1;

    }
}
