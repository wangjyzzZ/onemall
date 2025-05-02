package com.wood.onemall.order.dao;

import com.wood.onemall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author ${author}
 * @email ${email}
 * @date 2025-05-01 20:48:45
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
