package com.mpf.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author Silence
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	/**
	 * 
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		//handler就是处理器适配器要执行handler对象（只有method方法）
		//解析出异常
		CustomExecption customExecption = null;
		//如果是系统自定义的异常直接取出，在错误页面显示
			if (exception instanceof CustomExecption) {
				customExecption = (CustomExecption) exception;
			}else {
				customExecption = new CustomExecption("未知错误");
			}
		//如果不是系统自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
			//错误信息
			String message = customExecption.getMessage();
			
			ModelAndView modelAndView = new ModelAndView();
			
			modelAndView.addObject("message", message);
			modelAndView.setViewName("/items/error");
			return modelAndView;
	}

}
