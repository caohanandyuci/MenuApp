package com.ch.menuapp;

import com.ch.model.TestBaseAdapter;

import se.emilsjolander.stickylistheaders.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;

public class TestActivity extends Activity implements
		AdapterView.OnItemClickListener,
		StickyListHeadersListView.OnHeaderClickListener,
		StickyListHeadersListView.OnStickyHeaderOffsetChangedListener,
		StickyListHeadersListView.OnStickyHeaderChangedListener,
		View.OnTouchListener {

	private TestBaseAdapter mAdapter;
	private boolean fadeHeader = true;

	private StickyListHeadersListView stickyList;
	//private SwipeRefreshLayout refreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
		refreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								refreshLayout.setRefreshing(false);
							}
						}, 1000);
					}
				});*/

		mAdapter = new TestBaseAdapter(this);

		stickyList = (StickyListHeadersListView) findViewById(R.id.list);
		stickyList.setOnItemClickListener(this);
		stickyList.setOnHeaderClickListener(this);
		stickyList.setOnStickyHeaderChangedListener(this);
		stickyList.setOnStickyHeaderOffsetChangedListener(this);
		stickyList.addHeaderView(getLayoutInflater().inflate(
				R.layout.list_header, null));
		stickyList.addFooterView(getLayoutInflater().inflate(
				R.layout.list_footer, null));
		stickyList.setEmptyView(findViewById(R.id.empty));
		stickyList.setDrawingListUnderStickyHeader(true);
		stickyList.setAreHeadersSticky(true);
		stickyList.setAdapter(mAdapter);
		stickyList.setOnTouchListener(this);

	}

	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.clear:
			mAdapter.clear();
			break;
		case R.id.restore:
			mAdapter.restore();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "Item " + position + " clicked!",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onHeaderClick(StickyListHeadersListView l, View header,
			int itemPosition, long headerId, boolean currentlySticky) {
		Toast.makeText(this,
				"Header " + headerId + " currentlySticky ? " + currentlySticky,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onStickyHeaderOffsetChanged(StickyListHeadersListView l,
			View header, int offset) {
		if (fadeHeader
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			header.setAlpha(1 - (offset / (float) header.getMeasuredHeight()));
		}
	}

	@Override
	public void onStickyHeaderChanged(StickyListHeadersListView l, View header,
			int itemPosition, long headerId) {
		// Toast.makeText(this, "Sticky Header Changed to " + headerId,
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Toast.makeText(this, "OnTouch works", Toast.LENGTH_SHORT).show();
		v.setOnTouchListener(null);
		return false;
	}
}