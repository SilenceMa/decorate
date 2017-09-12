package com.mpf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpf.po.ItemsCustom;

@Controller
public class JsonTest {
	//请求json输出相应json
	//请求商品信息，输出商品信息,@RequestBody将请求的json转换为Java对象，@ResponseBody将itemCustom转换成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) throws Exception{
		return itemsCustom;
	}
	
	//请求key/value返回json串
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) throws Exception{
		return itemsCustom;
	}
}
