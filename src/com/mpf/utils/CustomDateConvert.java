package com.mpf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConvert implements Converter<String, Date>{

	@Override
	public Date convert(String source) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
