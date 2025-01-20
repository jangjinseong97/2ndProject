package com.green.jobdone.room.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatPostReq {


    @Schema(title = "채팅룸 pk",example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long roomId;

    @Schema(title = "채팅 내용")
    private String contents;

    @Schema(title = "채팅 플래그")
    private int flag;




    @JsonIgnore
    private long chatId;
}
