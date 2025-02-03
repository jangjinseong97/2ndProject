package com.green.jobdone.product;

import com.green.jobdone.product.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;

    public int postProduct(ProductPostReq p){

        Long businessId=mapper.checkBusinessProduct(p.getBusinessId());

        if(businessId ==null || businessId==0L){
            int result=mapper.postProduct(p);
            return result;
        }else {

            return 0;


        }
    }



    public int postOption(ProductOptionPostReq p) {

        List<String> list = mapper.checkOption(p.getDetailTypeId());

        if (list == null || list.size() == 0) {
            int result = mapper.postOption(p);

            return result;
        }

        for (String s : list) {
            if (s.equals(p.getName())) {
                return 0;
            }
        }

        int result = mapper.postOption(p);

        return result;

    }

    public List<ProductGetOption> getProductOption(){

        return mapper.getProductOption();
    }



    public int postProductOption(ProductOptionPostDto p){

        List<Long> list = mapper.checkProductOption(p.getProductId());

        if (list == null || list.size() == 0) {
            int result = mapper.postProductOption(p);

            return result;
        }

        for (Long s : list) {
            if (s.equals(p.getOptionId())) {
                return 0;
            }
        }


        int result = mapper.postProductOption(p);

        return result;

    }







    public int postOptionDetail(ProductOptionDetailPostReq p) {

        List<String> list = mapper.checkProductOptionDetail(p.getProductOptionId());

        if (list == null || list.size() == 0) {
            int result = mapper.postOptionDetail(p);

            return result;
        }

        for (String s : list) {
            if (s.equals(p.getName())) {
                return 0;
            }
        }

        int result = mapper.postOptionDetail(p);

        return result;


    }



    public List<ProductGetRes> getProductInfoByBusiness(long businessId) {

        List<ProductGetRes> list=mapper.getProductInfoByBusiness(businessId);

        return list;


    }

    public int updOptionDetail(ProductOptionDetailPatchReq p){
        int result = mapper.updOptionDetail(p);

        return result;
    }

    public int updProduct(ProductPatchReq p){
        int result = mapper.updProduct(p);

        return result;
    }


}
