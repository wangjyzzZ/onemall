package com.wood.onemall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.product.entity.SpuInfoEntity;
import com.wood.onemall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-04-29 22:31:04
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

