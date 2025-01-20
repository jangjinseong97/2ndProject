package com.green.jobdone.room.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatPicPostReq {
    @Schema(title = "채팅 pk",example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long chatId;

    @Schema(title = "채팅 사진", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pic;

    @JsonIgnore
    private long chatPicId;
}
