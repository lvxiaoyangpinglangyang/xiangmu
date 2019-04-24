package com.qf.j1808.mapper;

import com.qf.j1808.pojo.SolrItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ItemInfoMapper {

public List<SolrItemInfo> findAll();

//public List<SolrItemInfo> findItemByPage(@Param("begin") int begin,@Param("pageSize") int pageSize);
}
