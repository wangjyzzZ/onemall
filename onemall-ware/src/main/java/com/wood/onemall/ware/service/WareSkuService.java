package com.wood.onemall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.ware.entity.WareSkuEntity;
import com.wood.onemall.ware.vo.SkuHasStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-05-01 20:55:48
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);
}

