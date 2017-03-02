package com.ch.widget;

import com.ch.menuapp.R;
import com.ch.model.LeftAdapter;
import com.ch.model.RightAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class ProductFragment extends Fragment {
	private ListView rightListView;          //右侧商品listview
    private ListView leftListView;   //左侧--商品类型listview
    private RightAdapter rightAdapter;   //右侧adapter
    private LeftAdapter leftAdapter;  //左侧adapter
    private ImageView shopCart;//购物车
    private View cartFrame;
    private TextView buyNumView;//购物车上的数量标签
    private TextView mPriceSumView;
    //private View cartView;
    private TextView selectedView;
    private View titleLayout;
    private TextView title;
    private ListView popuListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.product_list, null);
		return view;
	}

}
