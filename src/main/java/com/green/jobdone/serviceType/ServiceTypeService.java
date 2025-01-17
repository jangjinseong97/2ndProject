package com.green.jobdone.serviceType;

import com.green.jobdone.serviceType.model.ServiceTypeGetReq;
import com.green.jobdone.serviceType.model.ServiceTypeGetRes;
import com.green.jobdone.serviceType.model.ServiceTypePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceTypeService {
    private final ServiceTypeMapper serviceTypeMapper;

    public int postCategory(ServiceTypePostReq p) {

        int exists = serviceTypeMapper.existCategory(p.getServiceTypeName());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        return serviceTypeMapper.insCategory(p);
    }

    public List<ServiceTypeGetRes> getCategory(ServiceTypeGetReq p) {
        return serviceTypeMapper.getCategory(p);
    }
}
