package com.ch.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import android.R.integer;
import android.util.SparseArray;

public class ProductManager {
	@SerializedName("error_code")
	public int mErrorCode = -1;
	@SerializedName("list_id")
	public int mListID=-1;
	@SerializedName("publish_time")
	public long mPublishTime = 0L;
	@SerializedName("product_list")
	public List<Product> mProducts = new ArrayList<Product>();
	@SerializedName("category_list")
	private String[] mCategoryList = {"干锅","凉菜","热菜","汤","粥","蒸菜","特价菜","套餐"};
	public void setProducts(List<Product> list){
		mProducts = list;
		for (Product p : mProducts) {
			mCache.append((int) p.mID, p);
		}
	}
	
	@Override
	public String toString() {
		return "ProductManager [mErrorCode=" + mErrorCode + ", mListID="
				+ mListID + ", mPublishTime=" + mPublishTime + ", mProducts="
				+ mProducts + ", mCategoryList="
				+ Arrays.toString(mCategoryList) + "]";
	}

	public List<String> getCategoryList(){
		List<String> lists = new ArrayList<String>();
		for(int i=0;i<mCategoryList.length;i++){
			lists.add(mCategoryList[i]);
		}
		return lists;
	}
	
	private SparseArray<Product> mCache  = new SparseArray<Product>();
	//获取每个分类下的商品数量
	public int getProductCountOfCategory(int categorykey){
		int count = 0;
		for(Product p:mProducts){
			if(categorykey == p.mCategory){
				count ++;
			}
		}
		return count;
	}
	public Product getProductByID(int id){
		return mCache.get(id);
	}
	
	public float getPrice(int key){
		return mCache.get(key).mPrice;
	}
	public int getCategoryItem(int key){
		return mCache.get(key).mCategory;
	}
	
	public String getCategory(int key){
		String defaultString = "其他";
		int category = mCache.get(key).mCategory;
		if(category<mCategoryList.length && category>=0){
			return mCategoryList[category];
		}
		return defaultString;
	}
	
}
