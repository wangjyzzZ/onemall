package com.wood.onemall.search.service;

import com.wood.onemall.search.vo.SearchParam;
import com.wood.onemall.search.vo.SearchResult;

public interface MallSearchService {

    SearchResult search(SearchParam param);
}
