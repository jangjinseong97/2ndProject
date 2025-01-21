package com.green.jobdone.like;

import com.green.jobdone.like.model.LikeReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    int deleteLikeInfo(LikeReq p);
    int postLikeInfo(LikeReq p);
}
