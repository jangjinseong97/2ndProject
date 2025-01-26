package com.green.jobdone.business.model.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessGetReq {
    @Schema(title = "대분류")
    private long categoryId;
    @Schema(title = "소분류")
    private long detailTypeId;

    @JsonIgnore
    private long signedUserId;

}
