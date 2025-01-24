package com.green.jobdone.product;

import com.green.jobdone.product.model.ProductOptionDetailPostReq;
import com.green.jobdone.product.model.ProductOptionPostReq;
import com.green.jobdone.product.model.ProductPostReq;
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


    public int postProductOption(ProductOptionPostReq p) {

        List<String> list = mapper.checkProductOption(p.getProductId());

        if (list == null || list.size() == 0) {
            int result = mapper.postProductOption(p);

            return result;
        }

        for (String s : list) {
            if (s.equals(p.getName())) {
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


}
