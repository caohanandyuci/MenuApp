package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
	
	private List<Order> mOrders = new ArrayList<Order>();
	
	private List<Product> mProducts  =null;
	
	public void setProductList(List<Product> list){
		mProducts = list;
	}
	
	public List<Order> getOrders() {
		return mOrders;
	}
	
	
	public void setOrders(List<Order> lists){
		mOrders = lists;
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
	
	
	
	public int getTotalCateCountByProduct(int ProductId){
		int count = 0;
		int categoryitem = mProducts.get(ProductId).mCategory;
		for(Order o:mOrders){
			if(o.mNumber>0){
				Product product = mProducts.get((int)o.mOrderID);
				if(ProductId==categoryitem){
					count += o.mNumber;
				}
			}
		}
		return count;
	}
	
	public void clearOrders(){
		mOrders.clear();
	}
}
