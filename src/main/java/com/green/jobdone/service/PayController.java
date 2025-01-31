package com.green.jobdone.service;

import com.green.jobdone.service.model.Dto.KakaoPayReq;
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
    public KakaoPayRedayRes startKakaoPay(@RequestParam Long serviceId) {
        log.info("serviceId : {}", serviceId);
        return payService.useKakaoPay(serviceId);
    }

    @PostMapping("/success")
    public ResponseEntity<KakaoPayRes> paySuccess(@RequestBody KakaoPayReq p) {
        KakaoPayRes kakaoPayRes = payService.payRes(p.getPgToken(),p.getServiceId(),p.getTid());



        return new ResponseEntity<>(kakaoPayRes, HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancel() {
        return new ResponseEntity<>("Payment cancelled.", HttpStatus.OK);
    }

    @GetMapping("/fail")
    public ResponseEntity<String> fail() {
        return new ResponseEntity<>("Payment failed.", HttpStatus.OK);
    }
}
