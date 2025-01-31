package com.green.jobdone.portfolio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PortfolioPutReq {

    @Schema(title = "portfolioId")
    private long portfolioId;

    @Schema(title = "category")
    private String category;

    @Schema(title = "detailType")
    private String detailType;

    @Schema(title = "price")
    private int price;

    @Schema(title = "takingTime")
    private String takingTime;

    @Schema(title = "title")
    private String title;

    @Schema(title = "contents")
    private String contents;






}
