package com.ch.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.ch.entity.OrderManager;
import com.ch.entity.Product;
import com.ch.menuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class LeftAdapter extends BaseAdapter implements Observer{

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
	
	public void UpdateSelectItem(ListView listview,int selectItem){
		this.selectItem = selectItem;
		//得到第一个可见item项的位置
        int visiblePosition = listview.getFirstVisiblePosition();
		for (int index = 0; index < listview.getChildCount(); index++) {
			View view = listview.getChildAt(index - visiblePosition);
			if(selectItem == (index-visiblePosition)){
				view.setBackgroundColor(Color.RED);
			}
			else{
				view.setBackgroundColor(Color.WHITE);
			}
		}
		
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.category_list, null);
			holder.mTextname = (TextView) convertView.findViewById(R.id.name);
			holder.mTextcount = (TextView) convertView.findViewById(R.id.count);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mTextname.setText(list.get(position));
		if (position == selectItem) {
			holder.mTextname.setBackgroundColor(Color.parseColor("#5CACEE"));
		} else {
			//holder.mTextname.setTextColor(Color.parseColor("#222222"));
			holder.mTextname.setBackgroundColor(Color.parseColor("#ffffff"));
		}
		int count = OrderManager.getInstance().getCategoryCount(position);
		if(count >0){
			holder.mTextcount.setVisibility(View.VISIBLE);
			holder.mTextcount.setTextColor(Color.WHITE);
			holder.mTextcount.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER);
			holder.mTextcount.setTextSize(10);
			holder.mTextcount.setText(String.valueOf(count));
		}
		else{
			holder.mTextcount.setVisibility(View.INVISIBLE);
		}
		convertView.setBackgroundDrawable(mContext.getResources().getDrawable(
				R.layout.list_item_selector));
		return convertView;
	}
	class ViewHolder {
		TextView mTextname;
		//产品个数
		TextView mTextcount;
		
	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		notifyDataSetInvalidated();
	}

}
