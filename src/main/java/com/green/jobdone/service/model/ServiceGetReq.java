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
    @Schema(name = "service_id", description = "서비스pk 값으로 견적서 or 예약현황 조회")
    private Long serviceId;
    public ServiceGetReq(Integer page, Integer size,
                         @BindParam("user_id")Long userId, @BindParam("business_id")Long businessId, @BindParam("service_id") Long serviceId)  {
        super(page, size);
        this.userId = userId;
        this.businessId = businessId;
        this.serviceId = serviceId;
    }
}