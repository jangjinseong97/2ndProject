package com.green.jobdone.business.pic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessOnePicsGetReq {
    @Schema(title = "businessId")
    private long businessId;
    public BusinessOnePicsGetReq(long businessId) {
        this.businessId = businessId;
    }
}
