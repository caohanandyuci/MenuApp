package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
	public List<Order> mOrders = new ArrayList<Order>();
	
	private List<Product> mProducts  =null;
	
	public void setProductList(List<Product> list){
		mProducts = list;
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
	
	public void clearOrders(){
		mOrders.clear();
	}
}
