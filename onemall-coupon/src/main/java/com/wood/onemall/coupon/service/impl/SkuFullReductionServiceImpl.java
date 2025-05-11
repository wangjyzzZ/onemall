package com.wood.onemall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wood.common.to.MemberPrice;
import com.wood.common.to.SkuReductionTo;
import com.wood.common.utils.PageUtils;
import com.wood.common.utils.Query;
import com.wood.onemall.coupon.dao.SkuFullReductionDao;
import com.wood.onemall.coupon.entity.MemberPriceEntity;
import com.wood.onemall.coupon.entity.SkuFullReductionEntity;
import com.wood.onemall.coupon.entity.SkuLadderEntity;
import com.wood.onemall.coupon.service.MemberPriceService;
import com.wood.onemall.coupon.service.SkuFullReductionService;
import com.wood.onemall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Autowired
    private SkuLadderService skuLadderService;
    @Autowired
    private MemberPriceService  memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        // 保存满减打折，会员价
        SkuLadderEntity ladderEntity = new SkuLadderEntity();
        ladderEntity.setSkuId(reductionTo.getSkuId());
        ladderEntity.setFullCount(reductionTo.getFullCount());
        ladderEntity.setDiscount(reductionTo.getDiscount());
        ladderEntity.setAddOther(reductionTo.getCountStatus());
        if (reductionTo.getFullCount() > 0) {
            skuLadderService.save(ladderEntity);
        }

        // 保存满减信息
        SkuFullReductionEntity fullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo, fullReductionEntity);
        if (reductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
            this.save(fullReductionEntity);
        }

        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();
        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(reductionTo.getSkuId());
            memberPriceEntity.setMemberLevelId(item.getId());
            memberPriceEntity.setMemberLevelName(item.getName());
            memberPriceEntity.setMemberPrice(item.getPrice());
            memberPriceEntity.setAddOther(1);
            return memberPriceEntity;
        }).filter(item -> item.getMemberPrice().compareTo(new BigDecimal("0")) == 1).collect(Collectors.toList());
        memberPriceService.saveBatch(collect);
    }

}