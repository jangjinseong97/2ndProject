package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePatchReq {
    private int completed;
    private long serviceId;
    private long userId;
    private Long businessId;
}
