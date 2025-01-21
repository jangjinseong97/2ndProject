package com.green.jobdone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//이거 어디감
public class ServicePostReq {
    @Schema(title = "서비스 받을 유저 pk", example = "2")
    private long userId;
    @Schema(title = "받고싶은 서비스 pk", example = "1")
    private long productId;
    private int price;
    private double lat;
    private double lng;
    private String address;
    private String comment;
    private String phone;
    @Schema(title = "일자, 시간 다작성", example = "2025/01/22 12:12:11")
    private String startTime;
    @Schema(title = "이름", example = "필수작성")
    private String name;
    @JsonIgnore
    private long serviceId;
}
