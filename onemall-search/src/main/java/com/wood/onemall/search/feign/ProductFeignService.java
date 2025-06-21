package com.wood.onemall.search.feign;

import com.wood.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("onemall-product")
public interface ProductFeignService {
    @RequestMapping("/product/attr/info/{attrId}")
        // @RequiresPermissions("product:attr:info")
    R info(@PathVariable("attrId") Long attrId);

    @RequestMapping("/product/brand/info/batch")
    R getBatch(@RequestBody List<Long> ids);
}
