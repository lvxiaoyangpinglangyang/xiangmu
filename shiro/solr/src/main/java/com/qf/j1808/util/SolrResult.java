package com.qf.j1808.util;

import lombok.Data;

import java.util.List;

@Data
public class SolrResult {

    private long recourdCount;//总记录数
    private List<?> itemList;//结果集

}
