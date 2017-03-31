package com.ch.view;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.Order;
import com.ch.entity.OrderManager;
import com.ch.entity.Product;
import com.ch.entity.ProductManager;
import com.ch.menuapp.MainActivity;
import com.ch.menuapp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


public class OrderDetailsView extends LinearLayout implements View.OnClickListener{

	private final static String TAG = "OrderDetailsView";
	
	public OrderDetailsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.d(TAG, "OrderDetailsView context");
		mRemarkContainerLayout = (LinearLayout) findViewById(R.id.remarkcontainer);
		mCommentContainerLayout = (LinearLayout) findViewById(R.id.commentcontainer);
	}

	
	public OrderDetailsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d(TAG, "OrderDetailsView context attrs");
		// TODO Auto-generated constructor stub
		mRemarkContainerLayout = (LinearLayout) findViewById(R.id.remarkcontainer);
		mCommentContainerLayout = (LinearLayout) findViewById(R.id.commentcontainer);
		mContext = context;
		setOnClickListener(this);

	}


	private MainActivity mMainActivity  = null;
	public void setMainActivity(MainActivity mian){
		mMainActivity = mian;
	}
	private LinearLayout mRemarkContainerLayout = null;
	private LinearLayout mCommentContainerLayout = null;
	private Context mContext = null;
	
	public LinearLayout getRemarkContLayout(){
		if(mRemarkContainerLayout==null){
			mRemarkContainerLayout = (LinearLayout) findViewById(R.id.remarkcontainer);
		}
		return mRemarkContainerLayout;
	}
	
	public LinearLayout getCommentConLayout(){
		if(mCommentContainerLayout == null){
			mCommentContainerLayout = (LinearLayout) findViewById(R.id.commentcontainer);
		}
		return mCommentContainerLayout;
	}
	
	private ImageView mProductImageView = null;
	public ImageView getProductImageView(){
		if(mProductImageView == null){
			mProductImageView = (ImageView) findViewById(R.id.productimage);
		}
		return mProductImageView;
	}
	
	private TextView mProductNameTextView = null;
	public TextView getProducNameTextView(){
		if(mProductNameTextView == null){
			mProductNameTextView = (TextView) findViewById(R.id.productname);
		}
		return mProductNameTextView;
	}
	
	private TextView mProductPriceTextView = null;
	public TextView getProductPriceTextView(){
		if(mProductPriceTextView == null){
			mProductPriceTextView = (TextView) findViewById(R.id.productprice);
		}
		return mProductPriceTextView;
	}
	
	private View.OnClickListener mRemarkClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "RemarkClickListener onclick");
			ViewGroup parent  = (ViewGroup) v.getParent();
			Log.d(TAG, "RemarkClickListener parent count:"+parent.getChildCount());
			for(int i=0;i<parent.getChildCount();i++){
				View childView = getRemarkContLayout().getChildAt(i);
				if(childView==v){
					childView.setBackgroundColor(Color.parseColor("#66CD00"));
					childView.setSelected(true);
					String priceString = String.format("价格: %s", mRemarksSpecLists.get(i).mPrice);
					getProducNameTextView().setText(priceString);
				}else{
					childView.setBackgroundColor(Color.WHITE);
					childView.setBackground(mContext.getResources().getDrawable(R.drawable.textview_border));
					childView.setSelected(false);
				}
			}
		}
	};
	
    private View.OnClickListener mCommentClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "RemarkClickListener onclick");
			
			if(v.isSelected()){
				v.setBackgroundColor(Color.WHITE);
				v.setBackground(mContext.getResources().getDrawable(R.drawable.textview_border));
				v.setSelected(false);
			}
			else{
				v.setBackgroundColor(Color.parseColor("#66CD00"));
				v.setSelected(true);
			}
		}
	};
	
	private View.OnClickListener mOKOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "mOKOnClickListener");
			
			String detailsString = "";
			
			LinearLayout remarksViewGroup = getRemarkContLayout();
			
			if(remarksViewGroup!=null && remarksViewGroup.getChildCount()>0){
				Order order = OrderManager.getInstance().getOrderByProductId(mProductId);
				if (order != null) {
					order.mMarkList = new ArrayList<String>();
					for (int i = 0; i < remarksViewGroup.getChildCount(); i++) {
						if (remarksViewGroup.getChildAt(i).isSelected()) {
							detailsString = detailsString
									+ mRemarksSpecLists.get(i).mDescirption
									+ ", ";
							order.mMarkList.add(mRemarksSpecLists.get(i).mDescirption);
							order.MarkId = i;
							order.mNumber++;
						}
					}
				}
				else{
					order = new Order();
					order.mNumber++;
					order.mOrderID = mProductId;
					order.mProduct = OrderManager.getInstance().mProductManager.mProducts.get(mProductId);
					order.mPrice = OrderManager.getInstance().mProductManager.mProducts.get(mProductId).mPrice;
					OrderManager.getInstance().getOrderCacheLists().add(order);
				}
			}
			
			LinearLayout commentViewGroupLayout = getCommentConLayout();
			if(commentViewGroupLayout!=null && commentViewGroupLayout.getChildCount()>0){
				Order order = OrderManager.getInstance().getOrderByProductId(mProductId);
				if (order != null) {
				for(int i=0;i<commentViewGroupLayout.getChildCount();i++){
					if(commentViewGroupLayout.getChildAt(i).isSelected()){
						detailsString = detailsString+mCommentLists.get(i)+", ";
						order.mCommentString +=  detailsString+mCommentLists.get(i)+", ";
					}
				}
				}
			}
			
			Log.d(TAG, "detailsString:"+detailsString);
			
			mMainActivity.getRightAdapter().notifyDataSetInvalidated();
			OrderManager.getInstance().notifyOrderObserver();
			
			if(mPopupWindow!=null){
				mPopupWindow.dismiss();
			}
		}
	};
	
	public void setOKOnclick(PopupWindow popupWindow){
		mPopupWindow = popupWindow;
		Button button = (Button) findViewById(R.id.okorderdetails);
		button.setOnClickListener(mOKOnClickListener);
	}
	
	private PopupWindow mPopupWindow = null;
	
	private int mProductId = -1;
	public void setProductId(int id){
		mProductId = id;
	}
	
	private List<Product.Spec> mRemarksSpecLists = null;
	public void setRemarkContainerLayout(List<Product.Spec> remarks){
		TextView textView = null;
		mRemarksSpecLists = remarks;
		getRemarkContLayout().removeAllViews();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(15,0,0,0);
		for(Product.Spec remark:remarks){
			textView = new TextView(mContext);
			textView.setText(remark.mDescirption);
			textView.setPadding(10, 5, 10, 5);
			textView.setBackground(mContext.getResources().getDrawable(R.drawable.textview_border));
			Log.d(TAG, "mRemarkContainerLayout:"+getRemarkContLayout());
			textView.setClickable(true);
			textView.setFocusable(true);
			textView.setOnClickListener(mRemarkClickListener);
			getRemarkContLayout().addView(textView,params);
		}
		getRemarkContLayout().getChildAt(0).setBackgroundColor(Color.parseColor("#66CD00"));
		String priceString = String.format("价格: %s", remarks.get(0).mPrice);
		getProducNameTextView().setText(priceString);
	}
	
	private List<String> mCommentLists = null;
	
	public void setCommentContainerLayout(List<String> comments){
		TextView textView = null;
		mCommentLists = comments;
		getCommentConLayout().removeAllViews();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(15,0,0,0);
		for(String comment:comments){
			textView = new TextView(mContext);
			textView.setText(comment);
			textView.setPadding(10, 5, 10, 5);
			textView.setOnClickListener(mCommentClickListener);
			textView.setBackground(mContext.getResources().getDrawable(R.drawable.textview_border));
			Log.d(TAG, "mCommentContainerLayout:"+getCommentConLayout());
			getCommentConLayout().addView(textView,params);
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d(TAG, "orderdetailsView onclick");
		switch (v.getId()){
        case R.id.okorderdetails:
            
            break;
    }
	}
	
}
