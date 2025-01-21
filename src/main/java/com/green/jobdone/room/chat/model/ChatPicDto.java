package com.green.jobdone.room.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatPicDto {
    private long chatId;
    private List<String> pic;
}
