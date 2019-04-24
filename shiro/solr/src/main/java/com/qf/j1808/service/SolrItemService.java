package com.qf.j1808.service;

import com.qf.j1808.util.SolrResult;
import com.qf.j1808.vo.MsgResult;

public interface SolrItemService {
    //同步数据库商品信息到solr索引库
    public MsgResult dataInputSolr();

    SolrResult queryItemsByPageAndKeyword(String keyword, Integer page, Integer pageSize);
}
