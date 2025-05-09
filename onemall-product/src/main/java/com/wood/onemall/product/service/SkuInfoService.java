package com.wood.onemall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-04-29 22:31:05
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

