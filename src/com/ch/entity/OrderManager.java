package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class OrderManager {
	
	private List<Order> mOrdersCacheLists = new ArrayList<Order>();
	
	private List<Order> mOrders = new ArrayList<Order>();
	public List<Order> getOrderCacheLists() {
		return mOrdersCacheLists;
	}
	
	public List<Order> getOrders(){
		mOrders.clear();
		Log.d("OrderManager", "mOrdersCacheLists"+mOrdersCacheLists.size()+",,"+mOrdersCacheLists.get(0).mNumber);
		for(Order o:mOrdersCacheLists){
			if(o.mNumber>0){
				mOrders.add(o);
			}
		}
		return mOrders;
	}
 	public int getOrderCount(){
		return mOrders.size();
	}
	public float getTotalPrice(){
		float totalprice = 0.0f;
		for(Order o:mOrders){
			totalprice += (o.mPrice*o.mNumber);
		}
		return totalprice;
	}
	
	public void modifyOrder(int id,int num){
		for(Order o:mOrders){
			if(id==o.mOrderID){
				o.mNumber = num;
				return;
			}
		}
	}
	
	public Order getOrderByProductId(int productid){
		Log.d("OrderManager","productid:"+productid+"size:"+mOrders.size());
		getOrders();
		Log.d("OrderManager","productid:"+productid+"size:111"+mOrders.size()+",,,"+mOrders.get(0).mOrderID);
		for(Order o:mOrders){
			if(o.mOrderID==productid){
				return o;
			}
		}
		return null;
	}
	
	
	public int getTotalCateCountByProduct(int ProductId){
		int count = 0;
		Product product = getOrderByProductId(ProductId).mProduct;
		int categoryitem = product.mCategory;
		for (Order o : mOrders) {
			if (o.mProduct.mCategory == categoryitem) {
				count += o.mNumber;
			}
		}
		return count;
	}
	
	public void clearOrders(){
		mOrders.clear();
	}
}
