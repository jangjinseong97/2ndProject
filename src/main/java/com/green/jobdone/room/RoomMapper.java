package com.green.jobdone.room;

import com.green.jobdone.room.chat.model.ChatPicDto;
import com.green.jobdone.room.chat.model.ChatPostReq;
import com.green.jobdone.room.model.RoomGetReq;
import com.green.jobdone.room.model.RoomGetRes;
import com.green.jobdone.room.model.RoomPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    int insRoom(RoomPostReq p);
    List<RoomGetRes> selRoom(RoomGetReq p);
}
