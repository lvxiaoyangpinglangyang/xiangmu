package com.qf.j1808;

import com.qf.j1808.mapper.ItemInfoMapper;
import com.qf.j1808.pojo.SolrItemInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrApplicationTests {

    @Test
    public void contextLoads() throws IOException, SolrServerException {
//	创建一个solrServer的客户端对象（类似httpClient）
       HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
        //		创建一个solr的文档对象
       SolrInputDocument doc = new SolrInputDocument();
//		给文档设置属性
        doc.setField("id","009");
        doc.setField("title","zhaouliu");
        //		添加文档到solr索引库中
        solrServer.add(doc);
        //		提交使生效
        solrServer.commit();
        System.out.println("add ok");

    }

    @Test
    public void testRead() throws SolrServerException {
       HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
      SolrQuery solrQuery = new SolrQuery();
      solrQuery.setQuery("*:*");
      solrQuery.setStart(0);
      solrQuery.setRows(10);
      solrQuery.set("df","title");
     QueryResponse queryResponse = solrServer.query(solrQuery);
     SolrDocumentList result = queryResponse.getResults();
     for(SolrDocument doc:result){
          Object id = doc.getFieldValue("id");
          Object title = doc.getFieldValue("title");
         System.out.println(id+":"+title);
     }
    }

    @Autowired
   private ItemInfoMapper itemInfoMapper;

    @Test
    public void testSolrMapper(){

      List<SolrItemInfo> all = itemInfoMapper.findAll();
        System.out.println(all.size());

    }
}
