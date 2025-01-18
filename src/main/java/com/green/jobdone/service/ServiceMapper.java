package com.green.jobdone.service;

import com.green.jobdone.service.model.ServicePostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceMapper {

    int insService(ServicePostReq p);
    int insServiceDetail(ServicePostReq p);
}
