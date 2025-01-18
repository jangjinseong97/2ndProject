package com.green.jobdone.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDetailDto {
    private long optionDetailId;
    private String detailName;
    private int detailPrice;
    private String contents;
}
