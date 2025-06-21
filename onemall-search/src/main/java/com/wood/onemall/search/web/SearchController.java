package com.wood.onemall.search.web;

import com.wood.onemall.search.service.MallSearchService;
import com.wood.onemall.search.vo.SearchParam;
import com.wood.onemall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @Autowired
    private MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        param.setQueryString(request.getQueryString());
        SearchResult result = mallSearchService.search(param);
        model.addAttribute("result", result);
        return "list";
    }
}
