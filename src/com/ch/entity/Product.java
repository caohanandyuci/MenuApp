package com.ch.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import android.R.string;

public class Product implements Comparable<Product>{
	public String nameString="";
	//商品索引值
	@SerializedName("id")  
	public long mID = -1;  
	//商品描述
	@SerializedName("description")  
	public String mDescirption = ""; 
	//商品名称
	@SerializedName("name")  
	public String mProductName = "";
	//商品价格
	@SerializedName("price")  
	public float mPrice = -1;
	//商品图片
	@SerializedName("url")  
	public String mImageUrlString = "";
	//商品分类
	@SerializedName("category")  
	public int mCategory = 0;
	//商品的计量单位
	@SerializedName("unitstring")  
	public String mUnitString = unitStrings[1];
	
	//产品的计量方式 0:标准计量方式按照克或者个计算，1:分为大份\中份\小份
	@SerializedName("mode")  
	public int mMode = 0;
	//是否卖光
	@SerializedName("exist")  
	public boolean mExist = true;
	@SerializedName("specs")  
	public List<Spec> mSpecStrings = null;
	//口味标记
	@SerializedName("mark")
	public List<String> mMarkStrings = null;
	
	
	
	
	public static class Spec{
		//描述大份\中份\小份
		@SerializedName("spec")
		public String mDescirption="";
		@SerializedName("price")
		public float mPrice = 0.0f;
		@Override
		public String toString() {
			return "Spec [mDescirption=" + mDescirption + ", mPrice=" + mPrice
					+ "]";
		}
	}
	
	public static String[] unitStrings = {"克","份","个","瓶"};

    public static String[] list = {"干锅","凉菜","热菜","汤","粥","蒸菜","特价菜","套餐"};

    
    
    
	
	@Override
	public String toString() {
		return "Product [nameString=" + nameString + ", mID=" + mID
				+ ", mDescirption=" + mDescirption + ", mProductName="
				+ mProductName + ", mPrice=" + mPrice + ", mImageUrlString="
				+ mImageUrlString + ", mCategory=" + mCategory
				+ ", mUnitString=" + mUnitString + ", mMode=" + mMode
				+ ", mExist=" + mExist + ", mSpecStrings=" + mSpecStrings.toString()
				+ ", mMarkStrings=" + mMarkStrings + "]";
	}





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
			product.mSpecStrings = new ArrayList<Product.Spec>();
			Product.Spec spec =  new Product.Spec();
			spec.mDescirption = "大份";
			spec.mPrice=100;
			product.mSpecStrings.add(spec);
			Product.Spec spec1 =  new Product.Spec();
			spec1.mDescirption = "中份";
			spec1.mPrice=80;
			product.mSpecStrings.add(spec1);
			Product.Spec spec2 =  new Product.Spec();
			spec2.mDescirption = "小份";
			spec2.mPrice=60;
			product.mSpecStrings.add(spec2);
			
			product.mMarkStrings = new ArrayList<String>();
			product.mMarkStrings.add("少辣");
			product.mMarkStrings.add("中辣");
			product.mMarkStrings.add("超辣");
			
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
	@Override
	public int compareTo(Product another) {
		// TODO Auto-generated method stub
        return this.mCategory - another.mCategory; // 按书的id比较大小，用于默认排序
	}

}
