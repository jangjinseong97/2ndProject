package com.green.jobdone.like.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeReq {
    private long userId;
    private long businessId;
}
