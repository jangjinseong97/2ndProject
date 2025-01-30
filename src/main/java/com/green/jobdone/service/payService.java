package com.green.jobdone.service;

import com.green.jobdone.common.KaKaoPay;
import com.green.jobdone.service.model.KakaoPayRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class payService {
    private final KaKaoPay kaKaoPay;
    private RestTemplate restTemplate = new RestTemplate();


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        // 스프링프레임워크꺼 써야됨
        String auth = "SECRET_KEY" + kaKaoPay.getSecretKey();
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public KakaoPayRes useKakaoPay(Long serviceId){

        //요청할걸 담는 부분
        Map<String , Object> params = new HashMap<>();
        params.put("cid",kaKaoPay.getCid()); //
        params.put("partner_order_id", "1234567890"); // 가맹점 주문번호
        params.put("partner_user_id", "user123"); // 가맹점 회원 ID
        params.put("item_name", "테스트 상품"); // 상품명
        params.put("quantity", 1); // 상품 수량
        params.put("total_amount", 10000); // 총 금액
        params.put("vat_amount", 1000); // 부가세
        params.put("tax_free_amount", 0); // 비과세 금액
        params.put("approval_url", "https://your-site.com/success"); // 결제 성공 시 이동할 URL
        params.put("cancel_url", "https://your-site.com/cancel"); // 결제 취소 시 이동할 URL
        params.put("fail_url", "https://your-site.com/fail"); // 결제 실패 시 이동할 URL



        //요청 부분
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, getHeaders());
        ResponseEntity<KakaoPayRes> response = restTemplate.exchange(
                "https://kapi.kakao.com/v1/payment/ready",
                HttpMethod.POST,
                requestEntity,
                KakaoPayRes.class
        );

        return response.getBody();
    }

}
