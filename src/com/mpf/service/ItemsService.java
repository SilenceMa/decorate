package com.mpf.service;

import java.util.List;

import com.mpf.po.ItemsCustom;
import com.mpf.po.ItemsQueryVo;

public interface ItemsService {
	//商品查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	/**
	 * 修改商品信息
	 * @param id 商品id
	 * @param itemsCustom 商品信息
	 * @throws Exception
	 */
	
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
	
	//根据商品id删除商品信息
	public void deleteItems(Integer []items_id) throws Exception;
}
