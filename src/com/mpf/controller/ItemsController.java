package com.mpf.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mpf.exception.CustomExecption;
import com.mpf.po.ItemsCustom;
import com.mpf.po.ItemsQueryVo;
import com.mpf.service.ItemsService;
import com.mpf.validation.ValidGroup1;

@Controller
//为了对URL进行分类管理，可以在这里定义根路径，最终访问路径URI跟路泾+子路泾
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	//商品分类
	//itemtypes表示最终将方法返回值放在request中的key
    @ModelAttribute("itemtypes")
    public Map<String, String> getItemTypes(){
       Map<String, String> itemTypes = new HashMap<String,String>();
       itemTypes.put("101", "数码");
       itemTypes.put("102", "母婴");
       return itemTypes;

    }
	
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception{
//		System.out.println(request.getParameter("id"));
		List<ItemsCustom> itemsCustoms = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		//在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList",itemsCustoms);
		
		/**
		 * 这里的视图名称全路径，需要在视图解析器中进行前缀和后缀的字符串
		 */
		modelAndView.setViewName("/items/itemsList");
		return modelAndView;
	}
	
	/**
	 * 修改商品信息页面展示
	 * 显示请求方法为post和get
	 */
	/*@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editItems() throws Exception{
		
		//调用service查询商品信息
		ItemsCustom itemsCustoms = itemsService.findItemsById(1);
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//将商品信息放入model
		modelAndView.addObject("itemsCustoms",itemsCustoms);
		
		*//**
		 * 这里的视图名称全路径，需要在视图解析器中进行前缀和后缀的字符串
		 *//*
		modelAndView.setViewName("/items/editItems");
		
		return modelAndView;
	}*/
	/**
	 * 
	 * @param model model是一个接口，modelMap是一个接口实现 。作用：将model数据填充到request域
	 * @param items_id @RequestParam 里面指定request传入参数名称和形参进行绑定 即 将页面中id的值绑定到自定义的items_id中
	 * @return 
	 * @throws Exception
	 * required = true 指定id是否必须传入，如果设置为true没有设置参数，那么会出错
	 * defaultValue  指定id是否必须传入，如果设置为true没有设置参数,使用默认值
	 */
	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
	public String editItems(Model model,@RequestParam(value="id",required = true,defaultValue="1") Integer items_id) throws Exception{
		
		//调用service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//判断商品信息是否为空，为空抛出 自定义异常
		if (itemsCustom==null) {
			throw new CustomExecption("参数错误");
		}
		model.addAttribute("itemsCustom", itemsCustom);
		return "/items/editItems";
	}
	
	/**
	 * 提交修改的商品信息
	 */
	/*@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit() throws Exception{
		//这里需要将页面传递过来的数据传入到service中进行商品信息的修改
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/success");
		
		//redirect:queryItems.action重定向到商品查询页面
		
		return "redirect:queryItems.action";
	}*/
	/**
	 * 
	 * @param request
	 * @param id
	 * @param itemsCustom
	 * @param @Validated 需要校验的pojo前面添加，
	 * @param bindingResult 校验的后面添加
	 * @return
	 * @throws Exception
	 * value={ValidGroup1.class}指定使用分组1的校验规则
	 * @ModelAttribute 指定pojo回显到页面中的key
	 */
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model,
			HttpServletRequest request,
			Integer id,
			@ModelAttribute("itemsCustom") 	@Validated(value={ValidGroup1.class}) 
			ItemsCustom itemsCustom,
			BindingResult bindingResult,
			MultipartFile item_pic
			) throws Exception{
		//获取校验的错误信息
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError objectError : errors) {
				System.out.println(objectError.getDefaultMessage());
			}
			
			model.addAttribute("errors", errors);
			return "/items/editItems";
		}
		//上传图片
		if (item_pic!=null) {
			//存储图片的物理路径
			String path = "F:\\upload";
			//原始图片名称
			String originNameString = item_pic.getOriginalFilename();
			
			//新图片名称
			String newfilename = UUID.randomUUID() + originNameString.substring(originNameString.lastIndexOf("."));
			File newfile = new File(path+newfilename);
			
			//将内存中的数据写入数据库
			item_pic.transferTo(newfile);
			//将新的图片名称传入itemsCustom中
			itemsCustom.setPic(newfilename);
		}
		
		
		//这里需要将页面传递过来的数据传入到service中进行商品信息的修改
		
		itemsService.updateItems(id, itemsCustom);
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/success");*/
		
		//redirect:queryItems.action重定向到商品查询页面
		
		return "/success";
	}
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer []items_id) throws Exception{
		
		//调用service批量删除商品
		//....
		itemsService.deleteItems(items_id);
		
		return "/success";
	}
	
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
	
	
	//批量商品修改页面，将商品信息查询出来，在页面中可以编辑商品信息
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception{
//		System.out.println(request.getParameter("id"));
		List<ItemsCustom> itemsCustoms = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		//在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList",itemsCustoms);
		
		/**
		 * 这里的视图名称全路径，需要在视图解析器中进行前缀和后缀的字符串
		 */
		modelAndView.setViewName("/items/editItemsQuery");
		return modelAndView;
	}
	//批量商品修改提交
	//通过itemsQueryVo接受提交的商品信息，将商品信息存储到itemsQueryVo中的itemsList属性中
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		return "/success";
	}
	
}
