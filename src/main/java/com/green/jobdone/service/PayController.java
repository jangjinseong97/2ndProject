package com.green.jobdone.service;

import com.green.jobdone.service.model.KakaoPayRedayRes;
import com.green.jobdone.service.model.KakaoPayRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
@Slf4j
public class PayController {
    private final PayService payService;

    @PostMapping("/ready")
    public KakaoPayRedayRes startKakaoPay(@RequestParam Long serviceId){
        log.info("serviceId : {}", serviceId);
        return payService.useKakaoPay(serviceId);
    }
    @PostMapping("/success")
    public ResponseEntity<KakaoPayRes> paySuccess(@RequestParam("pg_token") String pgToken,
                                                  @RequestParam Long serviceId,
                                                  @RequestParam String tid){
        KakaoPayRes kakaoPayRes = payService.payRes(pgToken, serviceId, tid);

        return new ResponseEntity<>(kakaoPayRes, HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public void cancel(){
        throw new RuntimeException();
    }

    @GetMapping("/fail")
    public void fail(){
        throw new RuntimeException();
    }
}
