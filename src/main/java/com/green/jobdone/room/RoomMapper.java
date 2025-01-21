package com.green.jobdone.room;

import com.green.jobdone.room.chat.ChatPicPostReq;
import com.green.jobdone.room.chat.ChatPostReq;
import com.green.jobdone.room.model.RoomGetReq;
import com.green.jobdone.room.model.RoomGetRes;
import com.green.jobdone.room.model.RoomPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    int insRoom(RoomPostReq p);
    int insChat(ChatPostReq p);
    int insChatPic(ChatPicPostReq p);
    List<RoomGetRes> selRoom(RoomGetReq p);
}
