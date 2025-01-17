package com.green.jobdone.category;

import com.green.jobdone.category.model.PostCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int insCategory(PostCategory p);
}
