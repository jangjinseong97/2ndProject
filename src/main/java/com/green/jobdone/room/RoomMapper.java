package com.green.jobdone.room;

import com.green.jobdone.room.model.RoomPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {
    int insRoom(RoomPostReq p);
}
