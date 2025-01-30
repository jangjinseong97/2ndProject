package com.green.jobdone.service.model.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Amount {
    private int total; // 총금액
    private int tax_free; // 비과세
    private int vat; // 부가세
    private int point; // 사용포인트 금액
    private int discount; // 할인 금액
    private int green_deposit; // 컵 보증금???

}
