package com.wood.onemall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.product.entity.AttrGroupEntity;
import com.wood.onemall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-04-29 22:31:05
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}

