package com.ch.entity;

import java.util.List;

public class Order {
	//所选商品的编号
	public long mOrderID = -1L;
	//商品的个数
	public int mNumber = 0;
	//订单金额
	public float mPrice = 0.0f;
	
	public Product mProduct = null;
	//下单时间
	public long time = 0L;
	
	public List<String> mMarkList = null;
	
	public String mCommentString = null;
}
