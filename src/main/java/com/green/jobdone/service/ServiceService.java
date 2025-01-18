package com.green.jobdone.service;

import com.green.jobdone.service.model.ServicePostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
