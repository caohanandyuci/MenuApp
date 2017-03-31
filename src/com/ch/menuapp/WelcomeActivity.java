package com.ch.menuapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		mStartButton = (Button) findViewById(R.id.start);
		mStartButton.setOnClickListener(mOnClickListener);
		mSettingButton = (Button) findViewById(R.id.setter);
		mSettingButton.setOnClickListener(mSettingOnClickListener);
	}
	private Button mStartButton = null;
	
	private View.OnClickListener mOnClickListener  = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(WelcomeActivity.this, MainActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			WelcomeActivity.this.startActivity(mIntent);
		}
	};
	private Button mSettingButton = null;
    private View.OnClickListener mSettingOnClickListener  = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent();
			mIntent.setClass(WelcomeActivity.this, SettingActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			WelcomeActivity.this.startActivity(mIntent);
		}
	};
}
