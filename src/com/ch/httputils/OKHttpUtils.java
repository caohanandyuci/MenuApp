package com.ch.httputils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.ch.menuapp.Const;
import com.ch.util.FIleUtils;
import com.ch.util.OnDownLoadListener;

import android.R.string;
import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpUtils {
	
	private final static String TAG = "OKHttpUtils";
	private static OkHttpClient mClient = new OkHttpClient();
	
	public static void DownLoadFileAsyn(final String url,final String savedir,final OnDownLoadListener listener){
		Request request = new Request.Builder().url(url).build();
		mClient.newCall(request).enqueue(new Callback(){

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
				Log.d(TAG, "DownLoadFileAsyn onFailure");
			}
			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				// TODO Auto-generated method stub
				Log.d(TAG, "DownLoadFileAsyn onResponse start");
				if(!response.isSuccessful()){
					Log.d(TAG,"code:"+response.code());
					listener.onDownloadFailed(response.code());
					return;
				}
				int len;
				byte[] buf = new byte[2048];
				try {
					InputStream inputStream = response.body().byteStream();
					// 可以在这里自定义路径
					File file = new File(savedir);
					Log.d(TAG, "body:"+response.toString());
					long total = response.body().contentLength();
					Log.d(TAG, "total:"+total);
					FileOutputStream fileOutputStream = new FileOutputStream(
							file);
					long sum = 0;
					while ((len = inputStream.read(buf)) != -1) {
						fileOutputStream.write(buf, 0, len);
						sum += len;
						int progress = (int) (sum * 1.0f / total * 100);
						// 下载中
						listener.onDownloading(progress);
					}
					fileOutputStream.flush();
					fileOutputStream.close();
					inputStream.close();
					listener.onDownloadSuccess();
				} catch (Exception e) {
					e.printStackTrace();
                    listener.onDownloadFailed(response.code());
                } 
                Log.d(TAG, "DownLoadFileAsyn onResponse end");
                Thread thead = new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							FIleUtils.UnZipFolder(Const.SAVE_PATH, Const.CACHE_PATH);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
                	
                });
                thead.start();
			}
		});
	}
	
	public static void UpLoadFileAsyn(String url, String orderString) {
		MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
		String postBody = orderString;

		Request request = new Request.Builder().url(url)
				.post(RequestBody.create(MEDIA_TYPE_TEXT, postBody)).build();
		Response response;
		try {
			response = mClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				Log.d(TAG, "server error: " + response);
			}
			Log.d(TAG, "response:" + response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


	

