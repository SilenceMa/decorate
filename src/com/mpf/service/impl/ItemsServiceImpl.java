package com.mpf.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mpf.exception.CustomExecption;
import com.mpf.mapper.ItemsMapper;
import com.mpf.mapper.ItemsMapperCustom;
import com.mpf.po.Items;
import com.mpf.po.ItemsCustom;
import com.mpf.po.ItemsQueryVo;
import com.mpf.service.ItemsService;

public class ItemsServiceImpl implements ItemsService{

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	//查询商品信息
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}


	//根据id查询商品信息
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//中间对商品信息进行业务处理
		//最终返回itemsCustom
		if (items==null) {
			throw new CustomExecption("参数错误");
		}
		
		
		ItemsCustom itemsCustom = null;
		//将items的内容拷贝到itemsCustom
		if (items!=null) {
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		
		return itemsCustom;
	}
	/**
	 * 修改商品信息
	 */
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id是否为空，如果为空抛出异常（异常处理最后将）
		//更新商品信息,使用updateByPrimaryKeyWithBLOBs可以更新items表中的所有字段，包括大文本类型
		//使用updateByPrimaryKeyWithBLOBs必须传入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}


	@Override
	public void deleteItems(Integer[] items_id) throws Exception {
		for (Integer integer : items_id) {
			itemsMapper.deleteByPrimaryKey(integer);
		}
		
	}
	
	
}
