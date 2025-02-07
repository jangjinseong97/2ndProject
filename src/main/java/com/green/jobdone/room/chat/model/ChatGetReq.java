package com.green.jobdone.room.chat.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class ChatGetReq {
    @Schema(name = "room_Id")
    private long roomId;

    public ChatGetReq(@BindParam("room_id") long roomId) {
        this.roomId = roomId;
    }
}
