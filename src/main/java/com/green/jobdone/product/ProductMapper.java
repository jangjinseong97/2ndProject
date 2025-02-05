package com.green.jobdone.product;

import com.green.jobdone.product.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int postProduct(ProductPostReq p);
    Long checkBusinessProduct(long businessId);
    int updProduct(ProductPatchReq p);

    int postOption(ProductOptionPostReq p);
    List<String> checkOption(long detailTypeId);
    List<ProductGetOption> getProductOption();

    int postProductOption(ProductOptionPostDto p);
    List<Long> checkProductOption(Long productId);


    int postOptionDetail(ProductOptionDetailPostReq p);
    List<String> checkProductOptionDetail(long productOptionId);
    int updOptionDetail(ProductOptionDetailPatchReq p);


    ProductGetRes getProductInfoByBusiness(long businessId);

}
