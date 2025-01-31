package com.green.jobdone.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.jobdone.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.BindParam;

@Slf4j
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReviewGetReq extends Paging {
    @JsonIgnore
    private Long userId;

    @Schema(title = "업체 PK", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long businessId;

    public ReviewGetReq(Integer page, Integer size
            , @BindParam("businessId") Long businessId) {
        super(page, size);
        this.businessId = businessId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}



