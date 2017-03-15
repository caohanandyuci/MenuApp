package com.ch.model;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.Order;
import com.ch.entity.OrderManager;
import com.ch.menuapp.R;
import com.ch.model.RightAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class OrderListAdapter extends BaseAdapter {

	public OrderListAdapter(Context ctx,OrderManager manager){
		mContext = ctx;
		mOrderManager = manager;
		UpdateAdapter();
	}
	private OrderManager mOrderManager = null;
	private Context mContext = null;
	private List<Order> mOrders = new ArrayList<Order>();
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

	public void UpdateAdapter(){
		mOrders.clear();
		mOrders = mOrderManager.getOrders();
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.product, null);
			holder.mImageView = (ImageView) convertView.findViewById(R.id.productbg);
			holder.mAddButton = (Button) convertView.findViewById(R.id.addbg);
			holder.mSubButton = (Button) convertView.findViewById(R.id.subbg);
			holder.mTextview = (TextView) convertView.findViewById(R.id.product_count);
			holder.mProductNameTextView = (TextView) convertView.findViewById(R.id.productname);
			holder.mProductPriceTextView = (TextView) convertView.findViewById(R.id.productprice);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mAddButton.setTag(position);
		holder.mAddButton.setOnClickListener(mAddOnClickListener);
		holder.mAddButton.setBackground(mContext.getResources().getDrawable(R.drawable.add));
		holder.mSubButton.setBackground(mContext.getResources().getDrawable(R.drawable.sub));
		holder.mSubButton.setOnClickListener(mSubOnClickListener);
		holder.mSubButton.setTag(position);
		holder.mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.timg));
		String mProductNameString = String.format("名称: %s", mOrders.get(position).mProduct.mPrice);
        holder.mProductNameTextView.setText(mProductNameString);
        String mProductPriceString = String.format("价格: %s 元/份", mOrders.get(position).mPrice);
        holder.mProductPriceTextView.setText(mProductPriceString);
        return convertView;
	}
	
	
	class ViewHolder {
		//产品图片
		ImageView mImageView;
		//加号按钮
		Button mAddButton;
		//产品个数
		TextView mTextview;
		//减号图片
		Button mSubButton;
		//产品名称
		TextView mProductNameTextView;
		//产品价格
		TextView mProductPriceTextView;
	}
	private OnClickListener mSubOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener mAddOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	};

}
