package com.green.jobdone.service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePatchReq {
    @NotNull(message = "completed는 필수로 입력해야 됩니다.")
    private int completed;
    @NotNull(message = "서비스 pk값은 필수로 입력해야 됩니다.")
    @Min(value = 1, message = "pk값은 1이상 정수입니다")
    private long serviceId;
    private long userId;
    private Long businessId;
}
