package com.green.jobdone.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class ServiceGetOneReq {
    @Schema(name = "service_id")
    private long serviceId;
    public ServiceGetOneReq(@BindParam("service_Id") long serviceId) {
        this.serviceId = serviceId;
    }
}
