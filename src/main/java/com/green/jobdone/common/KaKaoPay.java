package com.green.jobdone.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakaopay")
@Getter
@Setter
public class KaKaoPay {
    private String secretKey;
    private String cid;
}
