package com.green.jobdone.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
@ToString
public class ServiceGetOneReq {

    @NotNull(message = "서비스 pk값은 필수 입니다.")
    private long serviceId;
    private Long businessId;

}
