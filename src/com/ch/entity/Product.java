package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

import android.R.string;

public class Product {
	public String nameString="";
	//商品索引值
	public long mID = -1;  
	//商品描述
	public String mDescirption = ""; 
	//商品名称
	public String mProductName = "";
	//商品价格
	public float mPrice = -1;
	//商品图片
	public String mImageUrlString = "";
	//商品分类
	public int mCategory = 0;
	//商品的计量单位
	public String mUnitString = unitStrings[1];
	
	//产品的计量方式 0:标准计量方式按照克或者个计算，1:分为大份\中份\小份
	public int mMode = 0;
	//是否卖光
	public boolean mExist = true;
	
	public List<Spec> mSpecStrings = null;
	//口味标记
	public List<String> mMarkStrings = null;
	
	
	public static class Spec{
		//描述大份\中份\小份
		public String mDescirption="";
		public float mPrice = 0.0f;
	}
	
	public static String[] unitStrings = {"克","份","个","瓶"};

    public static String[] list = {"干锅","凉菜","热菜","汤","粥","蒸菜","特价菜","套餐"};

	
	public static List<Product> getTestData() {
		List<Product> mList = new ArrayList<Product>();
		int index = 0;
		
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "daimai" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 0;
			mList.add(product);
		}
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "qiezi" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 1;
			mList.add(product);
		}
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "doujiao" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 2;
			mList.add(product);
		}
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "miantiao" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 3;
			mList.add(product);
		}
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "daimai" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 4;
			mList.add(product);
		}
		
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "daimai" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 5;
			mList.add(product);
		}
		
		for (int i = 0; i < 10; i++) {
			index++;
			Product product = new Product();
			product.mID = index;
			product.mDescirption = "test" + String.valueOf(index);
			product.mProductName = "daimai" + String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 6;
			mList.add(product);
		}
		
		return mList;
	}
}
