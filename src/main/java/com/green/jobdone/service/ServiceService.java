package com.green.jobdone.service;

import com.green.jobdone.service.model.ServiceGetReq;
import com.green.jobdone.service.model.ServiceGetRes;
import com.green.jobdone.service.model.ServicePostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
