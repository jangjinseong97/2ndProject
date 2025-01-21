package com.green.jobdone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//이거 어디감
public class ServicePostReq {
    private long userId;
    private long productId;
    private int price;
    private double lat;
    private double lng;
    private String address;
    private String comment;
    private String phone;
    private String startTime;
    @JsonIgnore
    private long serviceId;
}
