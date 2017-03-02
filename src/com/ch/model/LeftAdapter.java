package com.ch.model;

import java.util.List;

import com.ch.entity.Product;
import com.ch.menuapp.MainActivity.ViewHolder;
import com.ch.menuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class LeftAdapter extends BaseAdapter {

	private List<Product> list = null;
    private Context mContext;

    public LeftAdapter(Context mContext, List<Product> list) {
        this.mContext = mContext;
        this.list = list;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@SuppressLint("ViewHolder") public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//ViewHolder viewHolder = null;
		//if(convertView == null){
		//	viewHolder = new ViewHolder();
		//	
		//}
		convertView=LayoutInflater.from(mContext).inflate(R.layout.product_list,null);
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		textView.setText("ceshis");
		return convertView;
	}
	

}
