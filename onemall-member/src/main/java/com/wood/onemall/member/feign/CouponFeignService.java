package com.wood.onemall.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("onemall-coupon")
public interface CouponFeignService {
}
