package com.green.jobdone.service;

import com.green.jobdone.service.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceMapper {

    int insService(ServicePostReq p);
    int insServiceDetail(ServicePostReq p);
    List<ServiceGetRes> GetServiceFlow(ServiceGetReq p);
    ServiceGetOneRes GetServiceOne(ServiceGetOneReq p);
}
