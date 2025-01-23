package com.green.jobdone.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
@ToString
public class ServiceGetOneReq {

    private long serviceId;

}
