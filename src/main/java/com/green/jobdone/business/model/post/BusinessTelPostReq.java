package com.green.jobdone.business.model.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BusinessTelPostReq {
    private long businessTelId;
    private long businessId;
    private String tel;

}
