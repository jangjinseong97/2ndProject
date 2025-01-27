package com.green.jobdone.business.pic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BusinessPicDto {
    @JsonIgnore
    private long businessPicId;
    private long businessId;
    private List<String> pics;
}
