package com.qf.j1808.service.impl;

import com.mysql.jdbc.StringUtils;
import com.qf.j1808.mapper.ItemInfoMapper;
import com.qf.j1808.pojo.SolrItemInfo;
import com.qf.j1808.service.SolrItemService;
import com.qf.j1808.util.SolrResult;
import com.qf.j1808.vo.MsgResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrItemServiceImpl implements SolrItemService {
    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public MsgResult dataInputSolr() {
       MsgResult msgResult = new MsgResult();
        try {
            List<SolrItemInfo> all = itemInfoMapper.findAll();
            HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
            for (SolrItemInfo item:all){
               SolrInputDocument doc = new SolrInputDocument();
                doc.setField("id",item.getId()+"  ");
                doc.setField("item_title","item.getItemTitle");
                doc.setField("item_sell_point",item.getItemSellPoint());
                doc.setField("item_price",item.getItemPrice());
                doc.setField("item_image",item.getItemImage());
                doc.setField("item_category_name",item.getItemCategoryName());
                doc.setField("item_desc",item.getItemDesc());

            solrServer.add(doc);
            }
            solrServer.commit();
            msgResult.setStatus(200);
            msgResult.setMessage("success");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return msgResult;
    }

    @Override
    public SolrResult queryItemsByPageAndKeyword(String keyword, Integer page, Integer pageSize) {
         SolrResult result = new SolrResult();

        try {
            HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
            SolrQuery solrQuery = new SolrQuery();
//        设置查询条件
            if(StringUtils.isNullOrEmpty(keyword)){//查询条件null
                solrQuery.setQuery("*:*");
            }else{
                solrQuery.setQuery(keyword);
            }
            //        计算起始记录号
            int begin = (page - 1) * pageSize;
            solrQuery.setStart(begin);//设置当前页起始记录号
            solrQuery.setRows(pageSize);//设置查询记录数
            solrQuery.set("df","item_keywords");//设置过滤的字段
            QueryResponse queryResponse = solrServer.query(solrQuery);
//            获取查询文档结果集
            SolrDocumentList results = queryResponse.getResults();
//            获取本次查询的记录数
            long total = results.getNumFound();
            int size = queryResponse.getResults().size();

//                  将文档对象集合转为item对象集合
            List<SolrItemInfo> itemList  = new ArrayList<>();
            for (SolrDocument doc: results    ) {
//                  将doccument转为item对象
                SolrItemInfo item = new SolrItemInfo();
                item.setId(Long.parseLong((String)doc.getFieldValue("id")));
                item.setItemPrice((long)doc.getFieldValue("item_price"));
                item.setItemTitle(doc.getFieldValue("item_title")+"");
                item.setItemCategoryName(doc.getFieldValue("item_category_name")+"");
                item.setItemDesc(doc.getFieldValue("item_desc")+"");
                item.setItemImage(doc.getFieldValue("item_image")+"");
                itemList.add(item);
            }
            result.setItemList(itemList);
            result.setRecourdCount(total);//查询总记录数
          return result;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
