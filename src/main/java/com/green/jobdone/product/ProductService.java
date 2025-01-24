package com.green.jobdone.product;

import com.green.jobdone.product.model.ProductPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;

    public int postProduct(ProductPostReq p){

        Long businessId=mapper.checkBusinessProduct(p.getBusinessId());

        if(businessId!=null){
            return 0;
        }

        int result=mapper.postProduct(p);





        return result;

    }
}
