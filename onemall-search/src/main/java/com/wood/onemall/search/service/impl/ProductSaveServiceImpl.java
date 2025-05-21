package com.wood.onemall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.wood.common.to.es.SkuEsModel;
import com.wood.onemall.search.config.OnemallElasticSearchConfig;
import com.wood.onemall.search.constant.EsConstant;
import com.wood.onemall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        skuEsModels.forEach(skuEsModel -> {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuEsModel), XContentType.JSON);
            bulkRequest.add(indexRequest);
        });

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, OnemallElasticSearchConfig.COMMON_OPTIONS);
        boolean hasFailures = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
        log.error("商品上架完成：{}, 返回数据：{}", collect, bulk.toString());
        return !hasFailures;
    }
}
