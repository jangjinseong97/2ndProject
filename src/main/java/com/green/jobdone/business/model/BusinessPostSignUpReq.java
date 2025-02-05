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

    @JsonIgnore
    private long businessId;
    @JsonIgnore
    private String paper;



    @Schema(title = "유저 아이디", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "사업자번호", example = "12245678910", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessNum;
    @Schema(title = "업체 이름", example = "싹 박멸해", requiredMode = Schema.RequiredMode.REQUIRED)
    private String businessName;
    @Schema(title = "업체 주소", example = "만경관근처", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;
    @Schema(title = "서비스 유형", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long detailTypeId;
    @Schema(title = "회사설립일", example = "2019/06/08", requiredMode = Schema.RequiredMode.REQUIRED)
    private String busiCreatedAt;
    @Schema(title = "회사전번", example = "0533836669", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tel;
    @Schema(title = "예비전번", example = "0533836669")
    private String tel2;
    @Schema(title = "예비전번", example = "0533836669")
    private String tel3;


}
