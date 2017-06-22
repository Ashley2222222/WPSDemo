package com.houjianbo.Utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

/**
 * @Description 用于处理文字的效果的工具类 
 * @Author LXHFIGHT
 * @Project gzcdcApp
 * @Package com.jiesai.gzcdc.util
 * @Date 2015-7-14-上午9:34:38
 * @Version
 */
public class WordUtil {
	
	/**
	 * 该方法用于将文字转换为上标
	 * @param source 需要转换的文字
	 * @return	转换结果，可以让TextView使用
	 */
	public Spanned getSuperScript(String source){
		Spanned result = Html.fromHtml(
				"<sup>" + source +
				"</sup>");
		return result;
	}
	
	/**
	 * 该方法用于将文字转为细体字
	 * @param source 需要转换的文字
	 * @return	转换结果，可为TextView使用
	 */
	public Spanned getLighterType(String source){
		Spanned result = Html.fromHtml(
				"<font style='font-size： 100'>"+
				source + 
				"</font>"
				);
		return result;
	}
	
	/**
	 * 根据存储在XML文件中的字符串的id获得字符串
	 * @param context	上下文对象
	 * @param stringId	目标字符串的id
	 * @return			获得字符串的结果， 如果为null则不存在该id
	 */
	public static String getStringById(Context context, int stringId){
		String str = context.getResources().getString(stringId);
		return str;
	}
	
	/**
	 * 
	 * 名称：判断是否全部为数字（包括小数点）的方法
	 * Copyright  jeisai All right reserved.
	 * @Title: isNumberic  
	 * @author liuxuhao
	 * @since 2015-8-12 下午8:04:09
	 * @param message
	 * @return  
	 * @return boolean    返回类型  
	 * @throws 
	 */
	public static boolean isNumberic(String message){
		if(message == null){
			return false;
		}
		
		char[] numbers = message.toCharArray();
		for(char c: numbers){
			if(((c <= '9') && (c >= '0')) || (c == '.')){
				
			}else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否数字(包括负数，含有小数点  )
	 * @param str
	 * @return
	 */
	public static boolean isNumberic2(String str){
		Boolean flag = str.matches("-?[0-9]*.?[0-9]*");
//	    if(flag == true) {
//	      System.out.println("Is Number!");
//	    } else {
//	      System.out.println("Is not Number!");
//	    }
	    return flag;
	}
}
