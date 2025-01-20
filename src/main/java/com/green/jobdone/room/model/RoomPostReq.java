package com.green.jobdone.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomPostReq {
    @Schema(title = "서비스 pk", requiredMode = Schema.RequiredMode.REQUIRED)
    private long serviceId;

    @JsonIgnore
    private long roomId;
}
