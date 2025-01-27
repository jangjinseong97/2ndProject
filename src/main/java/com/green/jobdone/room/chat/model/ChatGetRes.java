package com.green.jobdone.room.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatGetRes {
    private int flag;
    private long chatId;
    private String userName;
    private String businessName;
    private String logo;
    private String createdAt;
    private String contents;
    private List<GetPicDto> pics;
}
