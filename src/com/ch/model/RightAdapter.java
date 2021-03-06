package com.ch.model;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import uk.co.senab.bitmapcache.BitmapLruCache;

import com.ch.entity.Order;
import com.ch.entity.OrderManager;
import com.ch.entity.Product;
import com.ch.menuapp.Const;
import com.ch.menuapp.MainActivity;
import com.ch.menuapp.R;

import android.R.integer;
import android.R.raw;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

	private static String TAG = "RightAdapter";
	private final Context mContext;
	//private String[] mCountries;
	private int[] mSectionIndices;
	private Character[] mSectionLetters;
	private LayoutInflater mInflater;

	private BitmapLruCache mbitmapcache = null;
	
	private List<Product> mProducts = null;
	private List<Order> mOrderCacheLists = null;
	
	private ProductListener mProductListener = null;
	public void setProductListener(ProductListener listener){
		this.mProductListener = listener;
	}
	private OrderListener mOrderListener = null;
	public void setOrderListener(OrderListener listener){
		mOrderListener = listener;
	}
	public RightAdapter(Context context,List<Product> products) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mProducts = products;
		BitmapLruCache.Builder builder = new BitmapLruCache.Builder();
        builder.setMemoryCacheEnabled(true).setMemoryCacheMaxSizeUsingHeapSize();
		mbitmapcache = builder.build();
		//mCountries = context.getResources().getStringArray(R.array.countries);
//		mSectionIndices = getSectionIndices();
//		mSectionLetters = getSectionLetters();
	}
	public void setOrderCacheLists(List<Order> lists){
		mOrderCacheLists = lists;
		mOrderCacheLists.clear();
//		for(Product product:mProducts){
//			Order order = new Order();
//			order.mNumber = 0;
//			order.mOrderID = product.mID;
//			order.mPrice = product.mPrice;
//			order.mProduct = product;
//			mOrderCacheLists.add(order);
//		}
	}
	
//	private int[] getSectionIndices() {
//		ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
//		char lastFirstChar = mCountries[0].charAt(0);
//		sectionIndices.add(0);
//		for (int i = 1; i < mCountries.length; i++) {
//			if (mCountries[i].charAt(0) != lastFirstChar) {
//				lastFirstChar = mCountries[i].charAt(0);
//				sectionIndices.add(i);
//			}
//		}
//		int[] sections = new int[sectionIndices.size()];
//		for (int i = 0; i < sectionIndices.size(); i++) {
//			sections[i] = sectionIndices.get(i);
//		}
//		return sections;
//	}

//	private Character[] getSectionLetters() {
//		Character[] letters = new Character[mSectionIndices.length];
//		for (int i = 0; i < mSectionIndices.length; i++) {
//			letters[i] = mCountries[mSectionIndices[i]].charAt(0);
//		}
//		return letters;
//	}

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
		//Log.d("RightAdapter","getView position:"+position+",,convertView:"+convertView+",,count"+mOrders.get(position).mNumber);
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
		
		String imageurlString = Const.DIR_PATH+Const.IMAGE_PATH+mProducts.get(position).mImageUrlString;
		Log.d(TAG, "imageurl:"+imageurlString);
		if(!mbitmapcache.contains(imageurlString)){
			Bitmap bitmap = BitmapFactory.decodeFile(imageurlString);
			mbitmapcache.put(imageurlString, bitmap);
		}
		BitmapDrawable drawable = (mbitmapcache.get(imageurlString));
		holder.mImageView.setImageDrawable(drawable);
		String mProductNameString = String.format("名称: %s", mProducts.get(position).mProductName);
        holder.mProductNameTextView.setText(mProductNameString);
        String mProductPriceString = String.format("价格: %s 元/%s", mProducts.get(position).mPrice,mProducts.get(position).mUnitString);
        holder.mProductPriceTextView.setText(mProductPriceString);
        Order order = OrderManager.getInstance().getOrderByProductId(position);
        int num = 0;
        if(order == null){
        	    num = 0;
        }
        else{
        	    num = order.mNumber;
        }
		if (num == 0) {
			holder.mSubButton.setVisibility(View.INVISIBLE);
			holder.mTextview.setVisibility(View.INVISIBLE);
		}
		else{
			holder.mSubButton.setVisibility(View.VISIBLE);
			holder.mTextview.setVisibility(View.VISIBLE);
			holder.mTextview.setText(String.valueOf(num));
		}
		return convertView;

	}

	private OnClickListener mSubOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v!=null){
				int position = (Integer) v.getTag();
				OrderManager manager = OrderManager.getInstance();
				Order order =null;
				if(manager.isExistByProductId(position)){
					order = manager.getOrderByProductId(position);
					order.mNumber--;
					if(order.mNumber==0){
						manager.getOrderCacheLists().remove(order);
					}
				}
				else{
					
				}
				notifyDataSetInvalidated();
				OrderManager.getInstance().notifyOrderObserver();
				if(mOrderListener !=null){
					mOrderListener.OrderChanged(position);
				}
			}
		}

	};
	
	private OnClickListener mAddOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(v!=null){
				int position = (Integer) v.getTag();
				Product product = mProducts.get(position);
				if(product.mMarkStrings!=null && product.mMarkStrings.size()!=0){
					if(mProductListener!=null){
						mProductListener.ProductDetailList(position);
					}
				} else {
					Log.d("RightAdapter", "position:" + position + ",,,v:" + v);
					OrderManager manager = OrderManager.getInstance();
					Order order;
					if (manager.isExistByProductId(position)) {
						order = manager.getOrderByProductId(position);
						order.mNumber++;
					} else {
						order = new Order();
						order.mNumber++;
						order.mOrderID = position;
						order.mProduct = mProducts.get(position);
						order.mPrice = mProducts.get(position).mPrice;
						manager.getOrderCacheLists().add(order);
					}
					notifyDataSetInvalidated();
					OrderManager.getInstance().notifyOrderObserver();
					if (mOrderListener != null) {
						mOrderListener.OrderChanged((int) order.mProduct.mID);
						Log.d("RightAdapter", "===id:" + order.mProduct.mID);
					}
				}
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
//		mCountries = new String[0];
//		mSectionIndices = new int[0];
//		mSectionLetters = new Character[0];
//		notifyDataSetChanged();
	}

	public void restore() {
//		mCountries = mContext.getResources().getStringArray(R.array.countries);
//		mSectionIndices = getSectionIndices();
//		mSectionLetters = getSectionLetters();
//		notifyDataSetChanged();
	}

	class HeaderViewHolder {
		TextView text;
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
}
