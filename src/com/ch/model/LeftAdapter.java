package com.ch.model;

import java.util.List;

import com.ch.entity.Product;
import com.ch.menuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class LeftAdapter extends BaseAdapter {

	private List<String> list = null;
    private Context mContext;

    public LeftAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }
    public void setLists(List<String> list){
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

	public void update(ListView listview,int index,int num){
        //得到第一个可见item项的位置
        int visiblePosition = listview.getFirstVisiblePosition();
        //得到指定位置的视图，对listview的缓存机制不清楚的可以去了解下
        View view = listview.getChildAt(index - visiblePosition);
        TextView countview = (TextView) view.findViewById(R.id.count);
		if (num > 0) {
			countview.setVisibility(View.VISIBLE);
		} else {
			countview.setVisibility(View.INVISIBLE);
		}
        //countview.setText(num);
    }
	
	private int selectItem = 0;

	public int getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.category_list, null);
		TextView textView = (TextView) convertView.findViewById(R.id.name);
		textView.setText(list.get(position));
		if (position == selectItem) {
			textView.setBackgroundColor(Color.RED);
		} else {
			textView.setBackgroundColor(Color.WHITE);
		}
		convertView.setBackgroundDrawable(mContext.getResources().getDrawable(
				R.layout.list_item_selector));
		return convertView;
	}
	

}
