package com.green.jobdone.room.chat;

import com.green.jobdone.room.chat.model.ChatPicDto;
import com.green.jobdone.room.chat.model.ChatPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    int insChat(ChatPostReq p);
    int insChatPic(ChatPicDto p);
}
