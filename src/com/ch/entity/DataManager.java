package com.ch.entity;

import java.io.InputStream;
import java.util.List;

import com.ch.menuapp.Const;
import com.ch.util.CommonsUtils;

import android.content.Context;
import android.util.Log;

public class DataManager {
	private static String TAG = "DataManager";
	private DataManager(){};

	private static DataManager mInstance = null;

	public static DataManager getInstance() {
		if (mInstance == null) {
			synchronized (DataManager.class) {
				if (mInstance == null) {
					mInstance = new DataManager();
				}
			}
		}
		return mInstance;
	}
	
	private Context mContext  = null;
	public void InitManager(Context ctx){
		mContext = ctx;
	}
	public boolean readData(){
		InputStream stream = CommonsUtils.openFileInputStream(Const.DIR_PATH+Const.PRODUCT_LIST);
		if (stream != null) {
			mProductManager = (CommonsUtils.GsonToBean(mContext,ProductManager.class, stream));
			mProductManager.sortProducts();
		}
	    //Log.d(TAG, "productmanager:"+mProductManager.toString());
		return true;
	}
	
	
	public List<Product> getProductList() {
		return mProductManager.mProducts;
	}

	private ProductManager mProductManager = null;
	

}
