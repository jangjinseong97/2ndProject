package com.green.jobdone.service.model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceOptionDto {
    private long productOptionId;
    private String optionName;
    private List<OptionDetailDto> optionDetails;
}
