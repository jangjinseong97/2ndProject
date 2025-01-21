package com.green.jobdone.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class RoomGetReq {
    @Schema(name = "user_id")
    private Long userId;
    @Schema(name = "business_id")
    private Long businessId;

    @ConstructorProperties({"user_id", "business_id"})
    RoomGetReq(Long userId, Long businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }
}
