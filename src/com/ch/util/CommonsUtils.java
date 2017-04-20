package com.ch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class CommonsUtils {
	private static Gson gson = new Gson();
	private static String TAG = "CommonsUtils";
	public static FileInputStream openFileInputStream(String filename){
		if(filename!=null){
			File file = new File(filename);
			boolean exists = file.exists();
			Log.d(TAG,"filepath:"+filename +",file exists:"+exists);
			if(file.exists()){
				try {
					return new FileInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static <T> T GsonToBean(Context ctx,Class<T> cls,InputStream inputStream){
		T t  = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);// 使用BufferReader读取输入流中的数据；
			String line;
			StringBuilder stringBuilder = new StringBuilder();// 所有读取的json放到StringBuilder中，这里也可以使用StringBuffer,效果一样；
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			String jsonString = stringBuilder.toString();
			bufferedReader.close();// 按相反的顺序关闭流；
			inputStreamReader.close();
			// 调用使用GSON解析的方法;
			if (gson != null) {
				t = gson.fromJson(jsonString, cls);
			}
		} catch (IOException e) {  
            e.printStackTrace();  
        }  
		return t;
	}
}
