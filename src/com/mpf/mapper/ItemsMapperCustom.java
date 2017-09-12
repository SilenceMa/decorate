package com.mpf.mapper;

import java.util.List;

import com.mpf.po.ItemsCustom;
import com.mpf.po.ItemsQueryVo;

public interface ItemsMapperCustom {
    //查询商品列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}