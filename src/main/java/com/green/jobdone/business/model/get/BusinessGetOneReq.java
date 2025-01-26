package com.green.jobdone.business.model.get;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class BusinessGetOneReq {
    @Schema(title = "businessId")
    private long businessId;
    public BusinessGetOneReq(long businessId) {
        this.businessId = businessId;
    }
}
