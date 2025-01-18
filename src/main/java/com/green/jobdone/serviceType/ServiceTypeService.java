package com.green.jobdone.serviceType;

import com.green.jobdone.serviceType.detail.model.DetailTypeGetReq;
import com.green.jobdone.serviceType.detail.model.DetailTypeGetRes;
import com.green.jobdone.serviceType.detail.model.DetailTypePostReq;
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

        int exists = serviceTypeMapper.existServiceType(p.getServiceTypeName());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        return serviceTypeMapper.insServiceType(p);
    }

    public List<ServiceTypeGetRes> getCategory(ServiceTypeGetReq p) {

        return serviceTypeMapper.getServiceType(p);
    }

    //여기부터 디테일

    public int postDetailType(DetailTypePostReq p) {

        int exists = serviceTypeMapper.existDetailType(p.getServiceTypeId(),p.getName());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 존재하는 상세서비스입니다");
        }
        return serviceTypeMapper.insDetailType(p);

    }

    public List<DetailTypeGetRes> getDetailType(DetailTypeGetReq p) {
        return serviceTypeMapper.getDetailTypeList(p);
    }


}
