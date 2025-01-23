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
    int updService(ServicePutReq p);
    int updServiceDetail(ServicePutReq p);
    int updServiceOption(ServicePutReq p);

    int getCompleted(long serviceId);
    int putCompleted(ServicePutReq p);
}
