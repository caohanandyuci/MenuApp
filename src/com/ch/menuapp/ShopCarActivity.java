package com.ch.menuapp;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import com.ch.entity.OrderManager;
import com.ch.httputils.OKHttpUtils;
import com.ch.model.OrderListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShopCarActivity extends Activity {

	private final static String TAG = "ShopCarActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shopcar);
		mOrderListAdapter = new OrderListAdapter(this, OrderManager.getInstance());
		//stickyList.addHeaderView(getLayoutInflater().inflate(R.layout.list_header,null));
		stickyList = (StickyListHeadersListView) findViewById(R.id.orderlist);
		stickyList.setFastScrollEnabled(false);
		stickyList.setVerticalScrollBarEnabled(false);
		stickyList.addFooterView(getLayoutInflater().inflate(R.layout.list_footer, null));
		stickyList.setEmptyView(findViewById(R.id.empty));
		stickyList.setDrawingListUnderStickyHeader(true);
		stickyList.setAdapter(mOrderListAdapter);
		TextView textcount = ((TextView)findViewById(R.id.shopcarcount));
		String countString = String.format("数量:%s", OrderManager.getInstance().getTotalCount());
		textcount.setText(countString);
		TextView textprice = ((TextView)findViewById(R.id.totalprice));
		String priceString = String.format("价格: %s", OrderManager.getInstance().getTotalPrice());
		textprice.setText(priceString);
		
		Button backButton = (Button) findViewById(R.id.backbutton);
		backButton.setOnClickListener(mBackButtonListener);
		Button oKButton = (Button) findViewById(R.id.createorder);
		oKButton.setOnClickListener(mCreateOrderClickListener);
	}

    private OrderListAdapter mOrderListAdapter = null;
    private StickyListHeadersListView stickyList;
	private View.OnClickListener mBackButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(ShopCarActivity.this, MainActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			ShopCarActivity.this.startActivity(mIntent);
		}
	};
	
	private View.OnClickListener mCreateOrderClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub			
			final String orderString = OrderManager.getInstance().printfOrderString();
			Log.d(TAG, "OrderString:"+orderString);
			Thread thread  = new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					OKHttpUtils.UpLoadFileAsyn(Const.UPLOAD_SERVER_URL,orderString);
				}
				
			});
			thread.start();
			
			
			Toast tst = Toast.makeText(ShopCarActivity.this, "下单成功！", Toast.LENGTH_SHORT);
	        tst.show();
		}
	};
}
