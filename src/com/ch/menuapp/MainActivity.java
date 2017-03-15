package com.ch.menuapp;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView.OnStickyHeaderChangedListener;

import com.ch.entity.Order;
import com.ch.entity.OrderManager;
import com.ch.entity.Product;
import com.ch.entity.ProductManager;
import com.ch.model.LeftAdapter;
import com.ch.model.OrderListAdapter;
import com.ch.model.OrderListener;
import com.ch.model.ProductListener;
import com.ch.model.RightAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements ProductListener ,OrderListener,OnStickyHeaderChangedListener{

	private ProductManager mProductManager = new ProductManager();
	private OrderManager mOrderManager = new OrderManager();

	private PopupWindow mOrderList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View popupView = getLayoutInflater().inflate(R.layout.order_list, null);
		//ListView mOrderListView = (ListView) popupView.findViewById(R.id.orders);
		//OrderListAdapter mOrderListAdapter = new OrderListAdapter(this,mOrderManager);
		//mOrderListView.setAdapter(mOrderListAdapter);
		mOrderList = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		mOrderList.setTouchable(true);
		mOrderList.setOutsideTouchable(true);
		mOrderList.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
		mOrderList.setAnimationStyle(R.style.anim_menu_bottombar);
		mOrderList.getContentView().setFocusableInTouchMode(true);
		mOrderList.getContentView().setFocusable(true);
		mOrderList.setOnDismissListener(new OnDismissListener() {
	        @Override
	        public void onDismiss() {
	            // popupWindow隐藏时恢复屏幕正常透明度
	            setBackgroundAlpha(1.0f);
	        }
	    });
		
		
		mProductManager.setProducts(Product.getTestData());
		// 初始化左侧产品分类列表
		InitLeftCategoryList();
		mTotalPriceView = (TextView) findViewById(R.id.totalprice);
		Button mCreateOrderButton = (Button) findViewById(R.id.createorder);
		mCreateOrderButton.setOnClickListener(mCreateOrderClickListener);
		//初始化右侧产品详单
		rightAdapter = new RightAdapter(this, mProductManager.mProducts);
		rightAdapter.setOrderCacheLists(mOrderManager.getOrderCacheLists());
		rightAdapter.setProductListener(this);
		rightAdapter.setOrderListener(this);
		stickyList = (StickyListHeadersListView) findViewById(R.id.rightlist);
		// stickyList.setOnItemClickListener(this);
		// stickyList.setOnHeaderClickListener(this);
		stickyList.setOnStickyHeaderChangedListener(this);
		stickyList.setFastScrollEnabled(false);
		stickyList.setVerticalScrollBarEnabled(false);
		// stickyList.setOnStickyHeaderOffsetChangedListener(this);
		// stickyList.addHeaderView(getLayoutInflater().inflate(R.layout.list_header,
		// null));
		stickyList.addFooterView(getLayoutInflater().inflate(R.layout.list_footer, null));
		stickyList.setEmptyView(findViewById(R.id.empty));
		stickyList.setDrawingListUnderStickyHeader(true);
		//stickyList.setAreHeadersSticky(true);
		stickyList.setAdapter(rightAdapter);
	    //stickyList.setOnTouchListener(this);
		
		Button backButton = (Button) findViewById(R.id.backbutton);
		backButton.setOnClickListener(mBackButtonListener);
		
		Button shopcarButton = (Button) findViewById(R.id.shopcar);
		shopcarButton.setOnClickListener(mShopCarClickListener);
		
	}

	private View.OnClickListener mBackButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(MainActivity.this, WelcomeActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			MainActivity.this.startActivity(mIntent);
		}
	};
	
	private void InitLeftCategoryList() {
		this.leftListView = (ListView) findViewById(R.id.left);
		final LeftAdapter leftAdapter = new LeftAdapter(this, mProductManager.getCategoryList());
		this.leftListView.setAdapter(leftAdapter);
		this.leftListView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				// TODO Auto-generated method stub
				leftAdapter.setSelectItem(arg2);
				//leftAdapter.notifyDataSetInvalidated();
				Log.d("MainActivity", "arg2:"+arg2+",,,arg3:"+arg3);
				int srolltoposition = 0;
				for (int i = 0; i < arg2; i++) {
					srolltoposition += mProductManager.getProductCountOfCategory(i);
				}
				Log.d("MainActivity", "srolltoposition:"+srolltoposition+"stickyList:"+rightAdapter.getCount());
						if (srolltoposition < rightAdapter.getCount()) {
							stickyList.setSelection(srolltoposition);
						}
			}
		});
	}

	private StickyListHeadersListView stickyList; // 右侧商品listview
	private ListView leftListView; // 左侧--商品类型listview
	private RightAdapter rightAdapter; // 右侧adapter

	private TextView buyNumView;// 购物车上的数量标签
	private TextView selectedView;
	private View titleLayout;
	private TextView title;
	private ListView popuListView;
	private TextView mTotalPriceView = null;

	private View.OnClickListener mCreateOrderClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if (mOrderList != null && !mOrderList.isShowing()) {
				mOrderList.showAtLocation(findViewById(R.id.main_layout), Gravity.BOTTOM, 0, 0);
				setBackgroundAlpha(0.5f);
			   
            }
//			Toast tst = Toast.makeText(MainActivity.this, "下单成功！", Toast.LENGTH_SHORT);
//	        tst.show();
		}
	};

	private View.OnClickListener mShopCarClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(MainActivity.this, ShopCarActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			MainActivity.this.startActivity(mIntent);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void PriceChanged(float totalprice) {
		// TODO Auto-generated method stub
		String priceString = "金额:￥ " + String.valueOf(totalprice);
		mTotalPriceView.setText(priceString);
	}

	@Override
	public void OrderChanged(int position) {
		// TODO Auto-generated method stub
		int categoryitem = mOrderManager.getOrderByProductId(position).mProduct.mCategory;
		int num = mOrderManager.getTotalCateCountByProduct(position);
		LeftAdapter adapter = ((LeftAdapter)this.leftListView.getAdapter());
		Log.d("MainActivity", "OrderChanged num:"+num+",,categoryitem:"+categoryitem);
		adapter.update(this.leftListView, categoryitem, num);
		
	}

	@Override
	public void onStickyHeaderChanged(StickyListHeadersListView l, View header,
			int itemPosition, long headerId) {
		// TODO Auto-generated method stub
		Log.d("MainActivity", "itemPosition:"+itemPosition+",,headerId"+headerId);
		LeftAdapter adapter = ((LeftAdapter)this.leftListView.getAdapter());
		adapter.setSelectItem((int)headerId);
		adapter.UpdateSelectItem(this.leftListView,(int)headerId);
		//adapter.notifyDataSetInvalidated();
	}
	
	
	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 *            屏幕透明度0.0-1.0 1表示完全不透明
	 */
	public void setBackgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity) this).getWindow()
				.getAttributes();
		lp.alpha = bgAlpha;
		((Activity) this).getWindow().setAttributes(lp);
	}
	
}
