package com.green.jobdone.category;

import com.green.jobdone.category.model.CategoryPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int insCategory(CategoryPostReq p);
    int existCategory(String serviceTypeName);
}
