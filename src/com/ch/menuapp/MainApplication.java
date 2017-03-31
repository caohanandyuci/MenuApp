package com.ch.menuapp;

import com.ch.entity.DataManager;

import android.app.Application;
import android.util.Log;


public class MainApplication extends Application {

	private static String TAG = "MainApplication";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DataManager.getInstance().InitManager(getApplicationContext());
		Log.d(TAG, "oncreate readdata!");
		DataManager.getInstance().readData();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

}
