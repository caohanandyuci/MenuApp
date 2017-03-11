package com.ch.util;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.Order;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.StaticLayout;

public class FIleUtils {
	public final static String FILE_PATH = "order";
	
	public static void SaveOrder(Context context, List<Order> orders) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_PATH, Activity.MODE_PRIVATE);
		// 实例化SharedPreferences.Editor对象
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// 用putString的方法保存数据
		for (Order o : orders) {
			editor.putInt(String.valueOf(o.mOrderID), o.mNumber);
		}
		// 提交当前数据
		editor.apply();
	}
	public static List<Order> readOrder(Context context){
		List<Order> lists = new ArrayList<Order>(10);
		SharedPreferences sharedPreferences= context.getSharedPreferences(FILE_PATH,
                Activity.MODE_PRIVATE);
        //String userId=sharedPreferences.getString(USER_ID,"");
        
		return lists;
	}
	
}
