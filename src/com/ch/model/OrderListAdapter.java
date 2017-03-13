package com.ch.model;

import java.util.List;

import com.ch.entity.Order;
import com.ch.entity.OrderManager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class OrderListAdapter extends BaseAdapter {

	public OrderListAdapter(Context ctx,OrderManager manager){
		mContext = ctx;
		mOrderManager = manager;
	}
	private OrderManager mOrderManager = null;
	private Context mContext = null;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		List<Order> lists = mOrderManager.getOrders();
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).mNumber>0){
				count ++;
			}
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView  =new TextView(mContext);
		textView.setText("item"+position);
		textView.setHeight(40);
		return textView;
	}

}
