package com.wood.onemall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-04-29 22:31:05
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> list);
}

