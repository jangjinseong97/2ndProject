package com.green.jobdone.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessPostSignUpReq {

    @Schema(title = "사업자번호", example = "12245678910", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessNum;
    @Schema(title = "업체 이름", example = "싹 박멸해", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title = "업체 간단 설명", example = "벌레 잡아드립니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(title = "업체 상세 설명", example = "겨울방학이라서 베인충 마이충 티모충 유미서폿 맍죠? 다 잡아드립니다.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;
    @Schema(title = "오픈 시간", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String openingTime;
    @Schema(title = "마감 시간", example = "21:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String closingTime;
    @Schema(title = "업체 위치", example = "만경관근처", requiredMode = Schema.RequiredMode.REQUIRED)
    private String location;
    @Schema(title = "유저 아이디", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "서비스 유형", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long serviceTypeId;

    @JsonIgnore
    private String logo;
    @JsonIgnore
    private long businessId;
}
