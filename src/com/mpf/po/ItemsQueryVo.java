package com.mpf.po;

import java.util.List;

/**
 * 
 * @author Silence
 *	商品的包装对象，包装了查询条件
 */
public class ItemsQueryVo {
	private Items items;
	//商品信息扩展类
	private ItemsCustom itemsCustom;
	
	private List<ItemsCustom> itemsList;
	//用户信息
	private UserCustom userCustom;
	
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	public UserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
	
	
}
