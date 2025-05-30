package com.wood.onemall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wood.onemall.ware.entity.PurchaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-11-17 13:50:10
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
