package com.ch.model;

import java.util.List;

import com.ch.entity.Product;
import com.ch.menuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class RightAdapter extends BaseAdapter {

	private List<Product> list = null;
    private Context mContext;

    public RightAdapter(Context mContext, List<Product> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.list.size();
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


	@SuppressLint("NewApi") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=LayoutInflater.from(mContext).inflate(R.layout.product,null);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.productbg);
		ImageView addviewImageView = (ImageView) convertView.findViewById(R.id.addbg);
		addviewImageView.setBackground(mContext.getResources().getDrawable(R.drawable.add));
		ImageView subviewImageView = (ImageView) convertView.findViewById(R.id.subbg);
		subviewImageView.setBackground(mContext.getResources().getDrawable(R.drawable.sub));
		imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.timg));
		return convertView;
	}


	private Context getResources() {
		// TODO Auto-generated method stub
		return null;
	}

}
