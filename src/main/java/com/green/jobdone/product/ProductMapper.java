package com.green.jobdone.product;

import com.green.jobdone.product.model.ProductGetRes;
import com.green.jobdone.product.model.ProductOptionDetailPostReq;
import com.green.jobdone.product.model.ProductPostReq;
import com.green.jobdone.product.model.ProductOptionPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int postProduct(ProductPostReq p);
    Long checkBusinessProduct(long businessId);
    int postProductOption(ProductOptionPostReq p);
    List<String> checkProductOption(long productId);
    int postOptionDetail(ProductOptionDetailPostReq p);
    List<String> checkProductOptionDetail(long productOptionId);
    List<ProductGetRes> getProductInfoByBusiness(long businessId);

}
