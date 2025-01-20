package com.green.jobdone.product;

import com.green.jobdone.product.model.ProductPostReq;
import com.green.jobdone.product.option.ProductOptionPostReq;
import com.green.jobdone.product.option.detail.OptionDetailPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int insProduct(ProductPostReq p);
    int existProduct(long businessId, long detailTypeId);
    //옵션
    int insProductOption(ProductOptionPostReq p);
    int existProductOption(long productId, String name);

    //옵션디테일
    int insOptionDetail(OptionDetailPostReq p);
    int existOptionDetail(long productId, String name);

}
