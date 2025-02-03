package com.green.jobdone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.jobdone.service.model.Dto.PostOptionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//이거 어디감
public class ServicePostReq {
    @Schema(title = "서비스 받을 유저 pk", example = "2")
    private long userId;
    @Schema(title = "받고싶은 서비스 pk", example = "1")
    private long productId;
    private int totalPrice;
    private double lat;
    private double lng;
    private String address;
    private String comment;
    @JsonIgnore
    private String phone;
    @Schema(title = "년/월/일 작성", example =
            "2025/01/22")
    private String startDate;
    @Schema(title = "시작 시간", example = "11:00:00")
    private String mStartTime;
    @Schema(title = "이름", example = "필수작성")
    @JsonIgnore
    private String name;
    @Schema(title = "평수")
    private String pyeong;

    private List<PostOptionDto> options;
    @JsonIgnore
    private long serviceId;
}
