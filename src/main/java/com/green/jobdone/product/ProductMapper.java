package com.green.jobdone.product;

import com.green.jobdone.product.model.ProductPostReq;
import com.green.jobdone.product.option.ProductOptionPostReq;
import com.green.jobdone.product.option.detail.OptionDetailPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int postProduct(ProductPostReq p);
    long checkBusinessProduct(long businessId);

}
