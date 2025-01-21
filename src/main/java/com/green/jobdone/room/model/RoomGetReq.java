package com.green.jobdone.room.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomGetReq {
    private Long userId;
    private Long businessId;
}
