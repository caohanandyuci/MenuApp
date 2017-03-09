package com.ch.entity;

public class Product {
	public String nameString="";
	//商品索引值
	public long mID = -1;  
	//商品描述
	public String mDescirption = ""; 
	//商品名称
	public String mProductName = "";
	//商品价格
	public long mPrice = -1;
	//商品图片
	public String mImageUrlString = "";
	//商品分类
	public int mCategory = 0;
	
}
public static List<Product> getTestData(){
		List<Product> mList = new ArrayList<Product>();
		int index = 0;
		for(int i=0;i<10;i++){
			index++;
			Product product = new Product();
			product.mID =index;
			product.mDescirption = "test"+String.valueOf(index);
			product.mProductName = "qiezi"+String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 1;
			mList.add(product);
		}
		for(int i=0;i<10;i++){
			index++;
			Product product = new Product();
			product.mID =index;
			product.mDescirption = "test"+String.valueOf(index);
			product.mProductName = "doujiao"+String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 2;
			mList.add(product);
		}
		for(int i=0;i<10;i++){
			index++;
			Product product = new Product();
			product.mID =index;
			product.mDescirption = "test"+String.valueOf(index);
			product.mProductName = "miantiao"+String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 3;
			mList.add(product);
		}
		for(int i=0;i<10;i++){
			index++;
			Product product = new Product();
			product.mID =index;
			product.mDescirption = "test"+String.valueOf(index);
			product.mProductName = "daimai"+String.valueOf(index);
			product.mPrice = index;
			product.mCategory = 4;
			mList.add(product);
		}
		return mList;
	}
