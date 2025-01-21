package com.green.jobdone.business.pic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BusinessPicDto {
    private long businessId;
    private List<String> pics;
}
