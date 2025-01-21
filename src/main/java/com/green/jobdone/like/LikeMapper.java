package com.green.jobdone.like;

import com.green.jobdone.like.model.LikeGetRes;
import com.green.jobdone.like.model.LikePostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    int deleteLikeInfo(LikePostReq p);
    int postLikeInfo(LikePostReq p);
    List<LikeGetRes> getLikeInfo(long userId);
}
