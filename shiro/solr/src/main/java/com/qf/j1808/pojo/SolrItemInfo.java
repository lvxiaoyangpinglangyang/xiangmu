package com.qf.j1808.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SolrItemInfo implements Serializable {

    private long id;//
    private String itemTitle;//商品名
    private String itemSellPoint;//买点
    private long itemPrice;//单价
    private String itemImage;//图片地址
    private String itemCategoryName;//商品分类
    private String itemDesc;//商品描述


}
