package com.green.jobdone.service.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.jobdone.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;
@Getter
@Setter
@ToString(callSuper = true)
public class ServiceGetReq extends Paging {
    @Schema(name = "user_id", description = "사용자 이용한 서비스")
    @JsonIgnore
    private Long userId;
    @Schema(name = "business_id", description = "업체 제공한 서비스 미기입시 사용자가 이용한 서비스 조회")
    private Long businessId;
    @Schema(description = "예약0 결제1 견적2 마이페이지3")
    @NotNull(message = "status 는 필수로 입력해야 됩니다.")
    private int status;
    @Schema(name = "search_name", description = "이름으로 찾기 필수아님")
    private String searchName;
    public ServiceGetReq(Integer page, Integer size,
                         @BindParam("user_id")Long userId, @BindParam("business_id")Long businessId, int status, @BindParam("search_name") String searchName)  {
        super(page, size);
        this.userId = userId;
        this.businessId = businessId;
        this.status = status;
        this.searchName = searchName;
    }
}