package com.wood.onemall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author ${author}
 * @email ${email}
 * @date 2025-05-01 20:48:45
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

