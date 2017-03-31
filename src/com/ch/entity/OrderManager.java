package com.ch.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.google.gson.Gson;
import android.util.Log;


public class OrderManager extends Observable {
	
	private OrderManager(){}
	
	
	private static OrderManager mInstanceManager = null;
	
	public static synchronized OrderManager getInstance(){
		if (mInstanceManager==null) {
			mInstanceManager= new OrderManager();
		}
		return mInstanceManager;
	}
	private List<Order> mOrdersCacheLists = new ArrayList<Order>();
	
	public ProductManager mProductManager = null;
	public void Init(ProductManager productManager){
		mProductManager = productManager;
	}
	
	public String printfOrderString(){
		StringBuilder orderString = new StringBuilder();
		orderString.append("{\"order_list\":");
		for(Order order:mOrdersCacheLists){
			orderString.append("[\"product_id\":").append(order.mOrderID).
			append(",\"product_count\":").append(order.mNumber).
			append(",\"markId\":").append(order.MarkId).
			append(",\"product_String\":").append(order.mCommentString).append("],");
		}
		orderString.deleteCharAt(orderString.length()-1);  
		orderString.append("}");
		//Gson gson = new Gson();
		//return gson.toJson(mOrdersCacheLists);
		return orderString.toString();
	}
	
	public List<Order> getOrderCacheLists() {
		return mOrdersCacheLists;
	}
	
	public void notifyOrderObserver(){
		setChanged();
		notifyObservers();
	}
	
 	public int getOrderCount(){
		return mOrdersCacheLists.size();
	}
 	
 	public int getTotalCount(){
 		int count = 0;
 		for(Order o:mOrdersCacheLists){
 			count += o.mNumber;
 		}
 		return count;
 	}
 	
	public float getTotalPrice(){
		float totalprice = 0.0f;
		for(Order o:mOrdersCacheLists){
			totalprice += (o.mPrice*o.mNumber);
		}
		return totalprice;
	}
	
	public void modifyOrder(int id,int num){
		for(Order o:mOrdersCacheLists){
			if(id==o.mOrderID){
				o.mNumber = num;
				return;
			}
		}
	}
	
	public boolean isExistByProductId(int productid){
		for(Order o:mOrdersCacheLists){
			if(o.mOrderID==productid){
				return true;
			}
		}
		return false;
	}
	
	
	
	public Order getOrderByProductId(int productid){
		Log.d("OrderManager","productid:"+productid+"size:"+mOrdersCacheLists.size());
		//getOrders();
		for(Order o:mOrdersCacheLists){
			if(o.mOrderID==productid){
				return o;
			}
		}
		return null;
	}
	
	public int getCategoryCount(int category){
		int count = 0;
		for (Order o : mOrdersCacheLists) {
			if (o.mProduct.mCategory == category) {
				count += o.mNumber;
			}
		}
		
		return count;
	}
	
	public int getTotalCateCountByProduct(int ProductId){
		int count = 0;
		Product product = getOrderByProductId(ProductId).mProduct;
		int categoryitem = product.mCategory;
		for (Order o : mOrdersCacheLists) {
			if (o.mProduct.mCategory == categoryitem) {
				count += o.mNumber;
			}
		}
		return count;
	}
	
	public void clearOrders(){
		mOrdersCacheLists.clear();
	}
}
