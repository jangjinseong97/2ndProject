package com.green.jobdone.service.model;

import com.green.jobdone.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
@ToString(callSuper = true)
public class ServiceGetReq extends Paging {
    @Schema(name = "user_id")
    private Long userId;
    @Schema(name = "business_id")
    private Long businessId;

    public ServiceGetReq(Integer page, Integer size,
                         @BindParam("user_id")Long userId, @BindParam("business_id")Long businessId)  {
        super(page, size);
        this.userId = userId;
        this.businessId = businessId;

    }
}
