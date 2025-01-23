package com.green.jobdone.service;

import com.green.jobdone.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
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
    @Transactional
    public int completedService(ServicePutComReq p){
        int oldCompleted = serviceMapper.getCompleted(p.getServiceId());
        // 들고와서 비교

        // 작성/수정 누르면 0에서 1로 변경
        // 작성완료 >> 2로 변경
        // 2에서는 1에서 6으로 변경 가능
        // 3에선 4로 변경 가능
        // 6에선 7~로 변경 7>>8 8>>9
        return 0;

    }
}
