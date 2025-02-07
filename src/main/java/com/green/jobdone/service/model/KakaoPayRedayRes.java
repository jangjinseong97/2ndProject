package com.green.jobdone.service.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class KakaoPayRedayRes {
    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;
}
