package com.green.jobdone.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomPostReq {
    @Schema(title = "서비스 pk")
    private Long serviceId;
    @Schema(title = "user pk")
    @JsonIgnore
    private long userId;
    @Schema(title = "업체 pk")
    private long businessId;
    @JsonIgnore
    private long roomId;
}
