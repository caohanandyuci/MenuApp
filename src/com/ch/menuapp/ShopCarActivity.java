package com.ch.menuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class ShopCarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shopcar);
		
		Button backButton = (Button) findViewById(R.id.backbutton);
		backButton.setOnClickListener(mBackButtonListener);
	}

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
}
