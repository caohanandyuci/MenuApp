package com.ch.model;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

import com.ch.entity.Order;
import com.ch.entity.Product;
import com.ch.menuapp.MainActivity;
import com.ch.menuapp.R;

import android.R.integer;
import android.R.raw;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;



public class RightAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

	private final Context mContext;
	private String[] mCountries;
	private int[] mSectionIndices;
	private Character[] mSectionLetters;
	private LayoutInflater mInflater;

	private List<Product> mProducts = null;
	private List<Order> mOrders = new ArrayList<Order>(100);
	
	private ProductListener mProductListener = null;
	public void setProductListener(ProductListener listener){
		this.mProductListener = listener;
	}
	
	public RightAdapter(Context context,List<Product> products) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mProducts = products;
		mOrders.clear();
		for(Product product:mProducts){
			Order order = new Order();
			order.mNumber = 0;
			order.mOrderID = product.mID;
			order.mPrice = product.mPrice;
			mOrders.add(order);
		}
		mCountries = context.getResources().getStringArray(R.array.countries);
		mSectionIndices = getSectionIndices();
		mSectionLetters = getSectionLetters();
	}

	private int[] getSectionIndices() {
		ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
		char lastFirstChar = mCountries[0].charAt(0);
		sectionIndices.add(0);
		for (int i = 1; i < mCountries.length; i++) {
			if (mCountries[i].charAt(0) != lastFirstChar) {
				lastFirstChar = mCountries[i].charAt(0);
				sectionIndices.add(i);
			}
		}
		int[] sections = new int[sectionIndices.size()];
		for (int i = 0; i < sectionIndices.size(); i++) {
			sections[i] = sectionIndices.get(i);
		}
		return sections;
	}

	private Character[] getSectionLetters() {
		Character[] letters = new Character[mSectionIndices.length];
		for (int i = 0; i < mSectionIndices.length; i++) {
			letters[i] = mCountries[mSectionIndices[i]].charAt(0);
		}
		return letters;
	}

	@Override
	public int getCount() {
		return mProducts.size();
	}

	@Override
	public Object getItem(int position) {
		return mProducts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.product, null);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.productbg);
		Button addviewbutton = (Button) convertView.findViewById(R.id.addbg);
		addviewbutton.setTag(position);
		addviewbutton.setOnClickListener(mAddOnClickListener);
		addviewbutton.setBackground(mContext.getResources().getDrawable(R.drawable.add));
		Button subviewbutton = (Button) convertView.findViewById(R.id.subbg);
		subviewbutton.setBackground(mContext.getResources().getDrawable(R.drawable.sub));
		subviewbutton.setOnClickListener(mSubOnClickListener);
		subviewbutton.setTag(position);
		imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.timg));
		subviewbutton.setVisibility(View.INVISIBLE);
		TextView textView = (TextView) convertView.findViewById(R.id.product_count);
		textView.setVisibility(View.INVISIBLE);
		return convertView;

	}

	private OnClickListener mSubOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v!=null){
				int position = (Integer) v.getTag();
				Order order = mOrders.get(position);
				order.mNumber--;
				if(order.mNumber<0){
					order.mNumber =0;
				}
				LinearLayout parent = (LinearLayout) v.getParent();
				Log.d("RightAdapter", "order.mNumber:"+order.mNumber);
				if(order.mNumber==0){
					parent.getChildAt(0).setVisibility(View.INVISIBLE);
					parent.getChildAt(1).setVisibility(View.INVISIBLE);
					((TextView)parent.getChildAt(1)).setText(String.valueOf(order.mNumber));
				}
				((TextView)parent.getChildAt(1)).setText(String.valueOf(order.mNumber));
				
				if(mProductListener!=null){
					float totalprice = 0.0f;
					for(Order o:mOrders){
						totalprice += o.mPrice*o.mNumber;
					}
					mProductListener.PriceChanged(totalprice);
				}
				
				
			}
			
			Toast tst = Toast.makeText(mContext, v.getTag().toString(), Toast.LENGTH_SHORT);
	        tst.show();
		}

	};
	
	private OnClickListener mAddOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(v!=null){
				int position = (Integer) v.getTag();
				Log.d("RightAdapter", "position:"+position+",,,v:"+v);
				Order order = mOrders.get(position);
				order.mNumber++;
				LinearLayout parent = (LinearLayout) v.getParent();
				Log.d("RightAdapter", "order.mNumber:"+order.mNumber);
				if(order.mNumber>0){
					parent.getChildAt(0).setVisibility(View.VISIBLE);
					parent.getChildAt(1).setVisibility(View.VISIBLE);
					((TextView)parent.getChildAt(1)).setText(String.valueOf(order.mNumber));
				}
				
				if(mProductListener!=null){
					float totalprice = 0.0f;
					for(Order o:mOrders){
						totalprice += o.mPrice*o.mNumber;
					}
					mProductListener.PriceChanged(totalprice);
				}
				
				Toast tst = Toast.makeText(mContext, v.getTag().toString(), Toast.LENGTH_SHORT);
		        tst.show();
			}
		}

	};
	
	
	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder;

		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = mInflater.inflate(R.layout.header, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}

		//CharSequence headerChar = mCountries[position].subSequence(0, 1);
		//holder.text.setText(headerChar);

		String category = Product.list[mProducts.get(position).mCategory];
		holder.text.setText(category);
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		//return mCountries[position].subSequence(0, 1).charAt(0);
		return mProducts.get(position).mCategory;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= mSectionIndices.length) {
			section = mSectionIndices.length - 1;
		} else if (section < 0) {
			section = 0;
		}
		return mSectionIndices[section];
	}

	@Override
	public int getSectionForPosition(int position) {
		for (int i = 0; i < mSectionIndices.length; i++) {
			if (position < mSectionIndices[i]) {
				return i - 1;
			}
		}
		return mSectionIndices.length - 1;
	}

	@Override
	public Object[] getSections() {
		return mSectionLetters;
	}

	public void clear() {
		mCountries = new String[0];
		mSectionIndices = new int[0];
		mSectionLetters = new Character[0];
		notifyDataSetChanged();
	}

	public void restore() {
		mCountries = mContext.getResources().getStringArray(R.array.countries);
		mSectionIndices = getSectionIndices();
		mSectionLetters = getSectionLetters();
		notifyDataSetChanged();
	}

	class HeaderViewHolder {
		TextView text;
	}

	class ViewHolder {
		TextView text;
	}
}
