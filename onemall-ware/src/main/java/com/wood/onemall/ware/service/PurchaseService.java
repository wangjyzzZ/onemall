package com.wood.onemall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wood.common.utils.PageUtils;
import com.wood.onemall.ware.entity.PurchaseEntity;
import com.wood.onemall.ware.vo.MergeVo;
import com.wood.onemall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;


public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);


    void mergePurchase(MergeVo mergeVo);


    void received(List<Long> ids);


    void done(PurchaseDoneVo doneVo);


}

