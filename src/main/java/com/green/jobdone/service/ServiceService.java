package com.green.jobdone.service;

import com.green.jobdone.service.model.*;
import com.green.jobdone.service.model.Dto.ServiceEtcDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ServiceService {
    private final ServiceMapper serviceMapper;

    @Transactional
    public int postService(ServicePostReq p){
        int res1 = serviceMapper.insService(p);

        int res2 = serviceMapper.insServiceDetail(p);
        int res = serviceMapper.insServiceOption(p);
        return res;
    }

    public List<ServiceGetRes> getService(ServiceGetReq p){
        if(p == null || p.getUserId()==null && p.getBusinessId()==null){
            return new ArrayList<>();
        }
        List<ServiceGetRes> res = serviceMapper.GetServiceFlow(p);
        return res;
    }

    public ServiceGetOneRes getOneService(ServiceGetOneReq p){
        log.info("p:{}",p);

        if(p.getServiceId()==0){
            return null;
        }

        ServiceGetOneRes res = serviceMapper.GetServiceOne(p);
        List<ServiceEtcDto> dto = serviceMapper.GetEtc(p.getServiceId());
        res.setEtc(dto);
        return res;
    }

    @Transactional
    public int updService(ServicePutReq p){
        Long serviceProviderUserId = serviceMapper.providerUserId(p.getServiceId());
        if(!serviceProviderUserId.equals(p.getProviderUserId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"잘못된 요청 입니다.");
        }

        int res1 = serviceMapper.updService(p);
        int res2 = serviceMapper.updServiceDetail(p);
//        int res3 = serviceMapper.updServiceOption(p);
        return res2;
    }

    public int completedService(ServicePatchReq p){
        int com = serviceMapper.getCompleted(p.getServiceId());
        if(!transitionAllowed(com,p.getCompleted(),p.getBusinessId())) {
            throw new IllegalArgumentException("잘못된 상태 전환 요청입니다.");
        }
        if(p.getBusinessId()==null){
            int res = serviceMapper.patchCompleted(p);
            return res;
        }

        Long findUserId = serviceMapper.findUserId(p.getBusinessId());

        if(findUserId==null){
            throw new IllegalArgumentException("해당 서비스 제공 업체가 아닙니다.");
        }
        if(findUserId.equals(p.getUserId())){
            p.setUserId(0);
        }


            int res = serviceMapper.patchCompleted(p);
            return res;

    }

    private boolean transitionAllowed(int oldCompleted, int newCompleted, Long businessId){

        Map<Integer, List<Integer>> businessAllowed = Map.of(
                0, List.of(1, 5),
                1, List.of(2),
                2, List.of(5)
        );


        Map<Integer, List<Integer>> userAllowed = Map.of(
                0, List.of(1, 3, 5),
                1, List.of(2),
                2, List.of(1, 3, 5, 6),
                3, List.of(4),
                6, List.of(7),
                7, List.of(8, 9)
        );

        // 업체일 때 상태변환 허용
        if (businessId != null) {
            return businessAllowed.getOrDefault(oldCompleted, List.of()).contains(newCompleted);
        }

        // 유저일 때 상태변환 허용
        return userAllowed.getOrDefault(oldCompleted, List.of()).contains(newCompleted);
    }
}
