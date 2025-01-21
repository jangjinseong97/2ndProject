package com.green.jobdone.room.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomGetRes {
    private long roomId;
    private Long chatId;
    private String recentlyChat;
    private String roomCreatedAt;
    private String title;

}
