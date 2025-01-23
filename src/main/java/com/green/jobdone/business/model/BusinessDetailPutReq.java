package com.green.jobdone.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessDetailPutReq {
    @Schema(title = "로고" ,requiredMode = Schema.RequiredMode.REQUIRED)
    private String logo; // 이건 그린그램 사진없을때 프사 어떻게 수정했는지 그거 알아보기


    @Schema(title = "업체 pk", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private long businessId;
    @Schema(title = "유저 pk", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "오픈 시간", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String openingTime;
    @Schema(title = "마감 시간", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String closingTime;


    @Schema(title = "업체 간단 설명", example = "벌레 잡아드립니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(title = "업체 상세 설명", example = "겨울방학이라서 집벌레 많죠? 잡아드림.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;
}
