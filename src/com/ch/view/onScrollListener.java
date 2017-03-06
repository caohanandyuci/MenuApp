package com.ch.view;

public interface onScrollListener {
	// tab 点击事件
	  void onClick(int position);
	  // 1.滑动开始
	  void onScrollStart();
	  // 2.滑动结束
	  void onScrollStop();
	  // 3.触发 onScrolled()
	  void onScrolled();
	  // 用户手动滑, 触发的 onScrolled()
	  void onScrolledByUser();
	  // 程序调用 scrollTo(), 触发的 onScrolled()
	  void onScrolledByInvoked();
}
