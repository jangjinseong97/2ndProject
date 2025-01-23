package com.green.jobdone.service;

import com.green.jobdone.service.model.*;
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

        int res = serviceMapper.insServiceDetail(p);
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
        return res;
    }

    @Transactional
    public int updService(ServicePutReq p){
        if(p.getCompleted()>2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "불가능한 요청입니다.");
        }
        int res1 = serviceMapper.updService(p);
        int res2 = serviceMapper.updServiceDetail(p);
        int res3 = serviceMapper.updServiceOption(p);
        return res3;
    }

    public int completedService(ServicePatchReq p){
        int com = serviceMapper.getCompleted(p.getServiceId());
        if(!transitionAllowed(com,p.getCompleted())) {
            throw new IllegalArgumentException("잘못된 상태 전환 요청입니다.");
        }
            int res = serviceMapper.patchCompleted(p);
            return res;

    }

    private boolean transitionAllowed(int oldCompleted, int newCompleted){
        Map<Integer,List<Integer>> allowed = Map.of(
          0,List.of(1,3,5),
          1,List.of(2),
                2,List.of(1,6),
                3,List.of(4),
                6,List.of(7,9),
                7,List.of(8)
        );
        return allowed.getOrDefault(oldCompleted, List.of()).contains(newCompleted);
    }
}
