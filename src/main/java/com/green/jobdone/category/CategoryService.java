package com.green.jobdone.category;

import com.green.jobdone.category.model.CategoryPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    CategoryMapper categoryMapper;

    public int postCategory(CategoryPostReq p) {

        int exists = categoryMapper.existCategory(p.getServiceTypeName());
        if (exists > 0) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        return categoryMapper.insCategory(p);
    }
}
