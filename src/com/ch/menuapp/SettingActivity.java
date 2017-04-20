package com.ch.menuapp;

import com.ch.httputils.OKHttpUtils;
import com.ch.util.OnDownLoadListener;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class SettingActivity extends Activity {

	private static String TAG = "SettingActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		mUpdatedataButton = (Button) findViewById(R.id.updatedata);
		mUpdatedataButton.setOnClickListener(mUpdateDataClickListener);
		mUpdateStatusTextView = (TextView) findViewById(R.id.updatestatus);
		mBackButton =(Button) findViewById(R.id.backbutton);
		mBackButton.setOnClickListener(mBackOnClickListener);
	}

	private Button mUpdatedataButton = null;
	private Button mBackButton=null;
	private TextView mUpdateStatusTextView = null;
	private boolean isNeedUpdate = true;
	private View.OnClickListener mUpdateDataClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onclick isNeedUpdate:"+isNeedUpdate);
			if (isNeedUpdate == true) {
				mUpdateStatusTextView.setText("开始下载");
				OKHttpUtils.DownLoadFileAsyn(Const.DOWNLOAD_SERVER_URL,
						Const.SAVE_PATH, mDownLoadListener);
				isNeedUpdate = false;
			}
		}
	};
	private View.OnClickListener mBackOnClickListener =new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(SettingActivity.this, WelcomeActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			SettingActivity.this.startActivity(mIntent);
		}
	};
	
	
	private OnDownLoadListener mDownLoadListener = new OnDownLoadListener() {
		
		@Override
		public void onDownloading(int progress) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onDownloading isNeedUpdate:"+isNeedUpdate+",,,progress:"+progress);
		}
		
		@Override
		public void onDownloadSuccess() {
			// TODO Auto-generated method stub
			isNeedUpdate= true;
			Log.d(TAG, "onDownloadSuccess isNeedUpdate:"+isNeedUpdate);
			mUpdateStatusTextView.post(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mUpdateStatusTextView.setText("下载完成");
				}
				
			});
		}
		
		@Override
		public void onDownloadFailed(final int errorCode) {
			// TODO Auto-generated method stub
			isNeedUpdate = true;
			Log.d(TAG, "onDownloadFailed isNeedUpdate:"+isNeedUpdate);
			mUpdateStatusTextView.post(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mUpdateStatusTextView.setText("下载失败,errorCode="+String.valueOf(errorCode));
				}
				
			});
		}
	};
	
}
