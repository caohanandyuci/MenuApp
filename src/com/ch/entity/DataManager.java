package com.ch.entity;

import java.util.List;

public class DataManager {
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
	
	public void InitProduct(){
		
	}
	private List<Product> mProducts = null;

}
