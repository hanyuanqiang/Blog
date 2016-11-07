package com.hyq.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	//把日期类型转换为String
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}


	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	//获取当前日期字符串
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}

	public static String getPartOfMillis(){
		String s1 = String.valueOf(System.currentTimeMillis());
		return s1.substring(4,10);
	}

}

