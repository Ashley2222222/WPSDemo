package com.houjianbo.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * 
 * @Description 用于处理界面表现形式的工具类 
 * @Author LXHFIGHT
 * @Project gzcdcApp
 * @Package com.jiesai.gzcdc.util
 * @Date 2015-7-14-上午10:47:29
 * @Version
 */
public class UIUtil {
	
		/**
		 * 判断设别是否为平板电脑 
		 * @param context　上下文对象
		 * @return　判读结果　true为是false为否
		 */
		public static boolean isPad(Context context) {
		    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		    Display display = wm.getDefaultDisplay();

		    DisplayMetrics dm = new DisplayMetrics();
		    display.getMetrics(dm);  
		    double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		    double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		    // 利用勾股定理得出屏幕尺寸  
		    double screenInches = Math.sqrt(x + y);
		    // 大于6尺寸则为Pad  
		    if (screenInches >= 6.0) {  
		        return true;  
		    }  
		    return false;  
		}  
	
	
	/**
	 * 获得资源XML文件中指定id的颜色值
	 * @param context	上下文对象
	 * @param color_id	指定的颜色值ID
	 * @return	   		颜色值
	 */
	public static int getColor(Context context, int color_id){
		return context.getResources().getColor(color_id);
	}


	/**
	 * 设置checkBox状态反转,点击checkBox所在layout促发
	 */
	public static void setCheckBox(CheckBox cb) {
		if (cb.isChecked()) {
			cb.setChecked(false);
		} else {
			cb.setChecked(true);
		}
	}
	/**
	 * 下述两个方法用于获得设备像素级别的的宽度和高度的
	 * @param context 上下文对象
	 * @return getScreenHeight获取设备高度， getScreenWidth获取设备的宽度
	 */
	public static int getScreenHeight(Context context){
		int height = -1;
		height = context.getResources().getDisplayMetrics().heightPixels;
		return height;
	}

	public static int getScreenWidth(Context context){
		int width = -1;
		width = context.getResources().getDisplayMetrics().widthPixels;
		return width;
	}

	/**
	 *
	 * 名称：获取组件中的双精度浮点数
	 * Copyright  jeisai All right reserved.
	 * @Title: getDoubleFromView
	 * @author liuxuhao
	 * @since 2015-8-12 下午7:56:42
	 * @param view
	 * @return double 当值为Double.MIN_VALUE时说明职位空， 否则为
	 */
	public static double getDoubleFromView(View view){
		String message = isViewEmpty(view);
		double result = Double.MIN_VALUE;
		if((!TextUtils.isEmpty(message)) && (WordUtil.isNumberic(message))){
			result = Double.parseDouble(message);
		}
		return result;
	}
	/**
	 *
	 * 名称：判断组件中的文本是否为空
	 * Copyright  jeisai All right reserved.
	 * @Title: isViewEmpty
	 * @param view 接受判断的组件
	 * @return String  当不为空时返回值，空时返回null
	 * @Description: 仅限于TextView，EditText和Button
	 */
	public static String isViewEmpty(View view){
		String message = null;
		if(view instanceof TextView){
			message = ((TextView)view).getText().toString();
		}else if(view instanceof EditText){
			message = ((EditText)view).getText().toString();
		}else if(view instanceof Button){
			message = ((Button)view).getText().toString();
		}
		return message;
	}
	/**
	 * 设置一组中只有一个checkBox状态选中
	 */
	public static void setCheckBoxInGroup(List<CheckBox> cbg, CheckBox chosen) {
		int id = chosen.getId();
		for (CheckBox cb : cbg) {
			if (cb.getId() != id) {
				cb.setChecked(false);
			}
		}
	}
	/**
	 * 将View转换成Bitmap
	 * @param addViewContent
	 * @return
	 */

	public static Bitmap getViewBitmap(View addViewContent) {

		addViewContent.setDrawingCacheEnabled(true);

		addViewContent.measure(
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		addViewContent.layout(0, 0,
				addViewContent.getMeasuredWidth(),
				addViewContent.getMeasuredHeight());

		addViewContent.buildDrawingCache();
		Bitmap cacheBitmap = addViewContent.getDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

		return bitmap;
	}

	public static void setBtnClickable(List<Button> btns){
		for (Button btn :btns){
			btn.setClickable(true);
			btn.setFocusable(true);
		}
	}
	public static void setBtnUnclickable(List<Button> btns){
		for (Button btn :btns){
			btn.setClickable(false);
			btn.setFocusable(false);
		}
	}
}
