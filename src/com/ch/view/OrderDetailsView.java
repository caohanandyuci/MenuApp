package com.ch.view;

import java.util.List;

import com.ch.entity.Product;
import com.ch.entity.ProductManager;
import com.ch.menuapp.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
					childView.setBackgroundColor(Color.RED);
					childView.setSelected(true);
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
				v.setBackgroundColor(Color.RED);
				v.setSelected(true);
			}
		}
	};
	
	private View.OnClickListener mOKOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "mOKOnClickListener");
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
	public void setRemarkContainerLayout(List<Product.Spec> remarks){
		TextView textView = null;
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
	}
	
	public void setCommentContainerLayout(List<String> comments){
		TextView textView = null;
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
