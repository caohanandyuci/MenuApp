package com.ch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ch.entity.Order;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.StaticLayout;
import android.util.Log;

public class FIleUtils {
	public final static String FILE_PATH = "order";
	
	private final static String TAG = "FileUtils";
	
	public static void SaveOrder(Context context, List<Order> orders) {
		String filenameString = "";
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_PATH, Activity.MODE_PRIVATE);
		// 实例化SharedPreferences.Editor对象
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// 用putString的方法保存数据
		for (Order o : orders) {
			editor.putInt(String.valueOf(o.mOrderID), o.mNumber);
		}
		// 提交当前数据
		editor.apply();
	}
	public static List<Order> readOrder(Context context){
		List<Order> lists = new ArrayList<Order>(10);
		SharedPreferences sharedPreferences= context.getSharedPreferences(FILE_PATH,
                Activity.MODE_PRIVATE);
        //String userId=sharedPreferences.getString(USER_ID,"");
        
		return lists;
	}
	
	public static void UnZipFolder(String zipFileString, String outPathString) throws Exception {    
		Log.d(TAG, "UnZipFolder start filestring:"+zipFileString+",,outPathString:"+outPathString);
		
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));    
        ZipEntry zipEntry;    
        String szName = ""; 
        Log.d(TAG, "inZip:"+inZip.toString());
        while ((zipEntry = inZip.getNextEntry()) != null) {    
            szName = zipEntry.getName();    
            if (zipEntry.isDirectory()) {    
                // get the folder name of the widget    
                szName = szName.substring(0, szName.length() - 1);    
                File folder = new File(outPathString + File.separator + szName);    
                boolean flag = folder.mkdirs();
                Log.d(TAG, "mkdirs flag:"+flag);
            } else {    
              	Log.d(TAG, "mkdirs szName:"+(outPathString + File.separator + szName));
                File file = new File(outPathString + File.separator + szName);    
                file.createNewFile();    
                // get the output stream of the file    
                FileOutputStream out = new FileOutputStream(file);    
                int len;    
                byte[] buffer = new byte[1024];    
                // read (len) bytes into buffer    
                while ((len = inZip.read(buffer)) != -1) {    
                    // write (len) byte from buffer at the position 0    
                    out.write(buffer, 0, len);    
                    out.flush();    
                }    
                out.close();    
            }    
        }   
        inZip.close();   
        
        Log.d(TAG, "UnZipFolder end");
    }  
	
}
