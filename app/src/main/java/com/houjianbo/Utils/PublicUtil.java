package com.houjianbo.Utils;

import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**  
  * 通用检查工具类
  * Copyright  jeisai All right reserved.
  * @ClassName: PublicUtil 
  * @author zhangwei
  * @since 2014-2-20 下午1:05:18 
  * Description:  // 用于详细说明此程序文件完成的主要功能
  * Modify History: // 修改历史记录列表，每条修改记录应包括修改日期、修改者及内容简述  
*/

public class PublicUtil {

    /**  
     * 检查是否是空的list
      * @Title: checkEmptyList  
      * @author zhangwei
      * @since 2014-2-20 下午1:06:33
      * @param list
      * @return boolean    返回类型
    */
    public static boolean checkEmptyList (List list) {
        if ((list == null) || (list.size() == 0)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**  
     * 检查是否是空的Map
      * @Title: checkEmptyList  
      * @author zhangwei
      * @since 2014-2-20 下午1:06:33
      * @param map
      * @return boolean    返回类型
    */
    public static boolean checkEmptyMap (Map map) {
        if ((map == null) || (map.isEmpty())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**  
      * 检查字符串是否为空
      * @Title: checkEmptyString  
      * @author zhangwei
      * @since 2014-2-20 下午1:06:45
      * @param str
      * @return boolean    返回类型
    */
    public static boolean checkEmptyString (String str) {
        if(((str == null) || (str.trim().length() == 0))||"null".equals(str)) {
            return true;
        }else{
            return false;
        }
    }

    /**  
     * 转换字符串编码
      * @Title: convertStringEncode  
      * @author zhangwei
      * @since 2014-2-20 下午1:07:12
      * @param str
      * @param encode
      * @return String    返回类型
    */
    public static String convertStringEncode (String str, String encode) {
        // Not in use but interesting
        try {
            byte bytes[] = str.getBytes(encode);    // "ISO-8859-1");
            String UTFtekst = new String(bytes);
            return UTFtekst;
        }
        catch (UnsupportedEncodingException w) {
            System.out.println("cannot get this charset");
            return "error";
        }
    }

    /**  
      * 格式化float型数据
      * @Title: formatFloat  
      * @author zhangwei
      * @since 2014-2-20 下午1:07:24
      * @param source
      * @return String    返回类型
    */
    public static String formatFloat (float source) {
        return new DecimalFormat("######0.00").format(source);
    }
    
    /**
    * 名称：Object数字转String数组
    * @Title: Obj2String  
    * @author zhangsanjun
    * @since 2014-6-6 上午3:43:35
    * @param obj
    * @return  
    * @return String[]    返回类型
     */
    public static String[] Obj2String(Object[] obj){
    	String[] str = new String[obj.length];
//    	for(int i = obj.length-1;i >= 0;i--){
//    		str[obj.length-1-i] = obj[i].toString();
//    	}
    	for(int i = 0;i<obj.length;i++){
    		str[i] = obj[i].toString();
    	}
    	return str;
    }
    
    /**
    * 名称：根据mapvalue返回key值
    * @Title: Mapvalue2Mapkey  
    * @author zhangsanjun
    * @since 2014-6-10 上午6:48:19
    * @return  
    * @return String    返回类型
     */
    public static int Mapvalue2Mapkey(Map<Integer, Object> map, Object value){
    	int result = 0;
    	if(!PublicUtil.checkEmptyMap(map) 
    			&& !PublicUtil.checkEmptyString(value+"")){
    		Iterator<Integer> iter = map.keySet().iterator();
        	while (iter.hasNext()) {
        		Integer key = iter.next();
    			Object keyValue = map.get(key).toString();
    			if(keyValue.equals(value) || keyValue == value){
    				result = key;
    				break;
    			}
    		}
    	}
    	
    	return result;
    }
    
    
    /**
     * 名称：根据mapvalue返回key值
     * @Title: Mapvalue2Mapkey  
     * @author zhangsanjun
     * @since 2014-6-10 上午6:48:19
     * @return  
     * @return String    返回类型
      */
     public static String Mapvalue2MapkeyStr(Map<String, String> map, String value){
    	 String result = "";
     	if(!PublicUtil.checkEmptyMap(map) 
     			&& !PublicUtil.checkEmptyString(value+"")){
     		Iterator<String> iter = map.keySet().iterator();
         	while (iter.hasNext()) {
         		String key = iter.next();
     			Object keyValue = map.get(key).toString();
     			if(keyValue.equals(value) || keyValue == value){
     				result = key;
     				break;
     			}
     		}
     	}
     	
     	return result;
     }
     
    /**
     * 名称：根据mapvalue返回key值
     * @Title: Mapvalue2Mapkey  
     * @author zhangsanjun
     * @since 2014-6-10 上午6:48:19
     * @return  
     * @return String    返回类型
      */
     public static int Mapvalue2Mapkey(TreeMap<Integer, String> map, String value){
     	int result = 0;
     	if(!PublicUtil.checkEmptyMap(map) 
     			&& !PublicUtil.checkEmptyString(value+"")){
     		Iterator<Integer> iter = map.keySet().iterator();
         	while (iter.hasNext()) {
         		Integer key = iter.next();
     			Object keyValue = map.get(key).toString();
     			if(keyValue.equals(value) || keyValue == value){
     				result = key;
     				break;
     			}
     		}
     	}
     	
     	return result;
     }
    
    /**
    * 名称：获得字段数组位置
    * @Title: getArraySize  
    * @author zhangsanjun
    * @since 2014-7-23 上午4:01:20  
    * @return void    返回类型
     */
    public static int getArrayPosition(Object[] obj, String str){
    	int position = -1;
    	if(obj!=null){
    		for(int i = 0; i < obj.length;i++){
    			String temp = obj[i].toString();
    			if(temp.equals(str)){
    				position = i;
    			}
    		}
    	}
    	return position;
    }
    
    /**
    * 名称：截取字符串字节数-向前截取
    * @Title: subStr  
    * @author zhangsanjun
    * @since 2014-6-20 上午5:53:09
    * @param str
    * @param subSLength
    * @throws UnsupportedEncodingException
    * @return String    返回类型
     */
    public static String subStr(String str, int subSLength){
        if (str == null)  {
            return "";  
        }else{ 
        	String subStr = "";
        	try {
        		int tempSubLength = subSLength;//截取字节数
                subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串  
                int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度 
                //int subStrByetsL = subStr.getBytes().length;//截取子串的字节长度 
                // 说明截取的字符串中包含有汉字  
                while (subStrByetsL > tempSubLength){  
                    int subSLengthTemp = --subSLength;
                    subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);  
                    subStrByetsL = subStr.getBytes("GBK").length;
                    //subStrByetsL = subStr.getBytes().length;
                }  
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            return subStr; 
        }
    }
  
    /**
    * 名称：判断是否数字
    * @Title: isNumeric  
    * @author zhangsanjun
    * @since 2014-7-23 上午7:51:45
    * @param str
    * @return boolean    返回类型
     */
    public static boolean isNumeric(String str){
    	if(!checkEmptyString(str)){
    		for(int i = str.length();--i>=0;){   
    	    	if (!Character.isDigit(str.charAt(i))){
    	    	    return false;
    	    	}
    	    }
    	    return true;
    	}else{
    		return false;
    	}  
    }
    
    /**
     * 
     * 名称：判断一个数字是否在一个闭区间内
     * @Title: isInRange  
     * @author liuxuhao
     * @since 2015-8-13 上午8:54:13
     * @param number 待判断数值
     * @param min 	 左边界
     * @param max	 右边界
     * @return boolean  判断结果，true表示在该范围内， false表示不再范围内
     */
    public static boolean isInRange(double number, double min, double max){
    	double maxBorder = min > max ? min : max;
    	double minBorder = min < max ? min : max;
    	if((number <= maxBorder) && (number >= minBorder)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * 
     * 名称：
     * 计算数组中从from-1下标到end-1下标之间的数的总和
     * 计算数据中从0下标到end-1下标之间的数总和
     * @Title: sum  
     * @author liuxuhao
     * @since 2015-8-13 上午9:58:33
     * @param datas
     * @param from
     * @param end
     * @return  计算结果 ：如果返回Double.MIN_VALUE的时候则说明传入的值有问题
     * @return double    返回类型
     */
    public static double sum(double[] datas, int from, int end){
    	double result = 0;
    	if(datas == null){
    		return Double.MIN_VALUE;
    	}
    	if((from <= 0) || (end > datas.length) || (from > end)){
    		return Double.MIN_VALUE;
    	}else{
    		for(int i = from - 1; i < end; i++){
    			result += datas[i];
    		}
    		return result;
    	}
    }
    public static double sum(double[] datas, int end){
    	double result = 0;
    	if(datas == null){
    		return Double.MIN_VALUE;
    	}
    	if( (end > datas.length) ){
    		return Double.MIN_VALUE;
    	}else{
    		for(int i = 0; i < end; i++){
    			result += datas[i];
    		}
    		return result;
    	}
    }
    
    /**
     * 根据字符串拆分
     * @param str
     * @param regex
     * @param getArrNo 取拆分后第几个数值(如果该数值大于拆分数组,返回字符"0")
     * @return
     */
    public static String getArray(String str, String regex, int getArrNo){
    	String[] arr = str.split(regex);
    	if(arr.length >= getArrNo){
    		return arr[getArrNo-1].toString();
    	}else{
    		return "0";
    	}
    }
    
    /**
     * 检查string是否为空,如果为空,返回字符串0,如果有,返回原来值
     * default 0
     * @param str
     * @return
     */
	public static String checkEmptyString2(String str) {
		if (((str == null) || (str.trim().length() == 0)) || "null".equals(str)) {
			return "0";
		} else {
			return str;
		}
	}
	
	 /**
     * 检查string是否为空,如果为空,返回空字符串,如果有,返回原来值
     * default ""
     * @param str
     * @return
     */
	public static String checkEmptyString3(String str) {
		if (((str == null) || (str.trim().length() == 0)) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
	
	/**
	 * 检查list中的EditText内容是否为空
	 * true都填写数据,false存在未填写的。
	 * @return
	 */
	public static boolean checkEditTextListFilled(List<EditText> list) {
		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			if (PublicUtil.checkEmptyString(list.get(i).getText().toString())) {
				flag = false;
				break;
			} else {
				flag = true;
			}
		}
		return flag;
	}

}
