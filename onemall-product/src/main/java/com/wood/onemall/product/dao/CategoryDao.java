package com.wood.onemall.product.dao;

import com.wood.onemall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ${author}
 * @email ${email}
 * @date 2025-04-29 22:31:05
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
