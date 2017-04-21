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
	
	public static boolean isExist(String path) {
		return ((File) new File(path)).exists();
	}
	
	public static boolean isEmpty(String path) {
		if (isExist(path)) {
			File f = new File(path);
			return f.length() == 0;
		}
		else
			return true;
	}
	
	public static boolean delete(String path) {
		try {
			return new File(path).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public static boolean deleteFile(File file) {
		Log.d(TAG, "delete: " + file.getAbsolutePath());
		boolean isDelete = false;
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				isDelete &= file.delete(); // delete()方法 ;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					isDelete &= deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			isDelete &= file.delete();
		} else {
			Log.d(TAG, "delete file not exist: " + file.getPath());
		}
		return isDelete;
	}
	
	public static boolean StringToFile(String res, String filePath) { 
		try {
			(new File(filePath)).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	File distFile = new File(filePath); 
        if (!distFile.getParentFile().exists()){ 
        	distFile.getParentFile().mkdirs(); 
        }
        try {
        	distFile.createNewFile();
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
	        bufferedWriter.write(res);
	        bufferedWriter.flush();
	        bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
       return true; 
    }
	
	 public static String FileToString(String filePath) { 
	        File file = new File(filePath);
	        if(!file.exists()){
	        	return null;
	        }
	        StringBuilder sb = new StringBuilder();  
	        try {
	        	BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	        	String line;
				while((line=bufferedReader.readLine())!=null){  
				    sb.append(line);  
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 

	        return sb.toString();  
	    }
	
		public static boolean writeToFile(File file,String data){
			
			if(file == null || TextUtils.isEmpty(data)) {
				return false;
			}
			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream(file, true);
				outStream.write(data.getBytes(HTTP.UTF_8));
				return true;
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(outStream!=null){
						outStream.flush();
						outStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}   
			}
			
			return false;
		}
		
		public static FileInputStream readFiles(String absolutePath){
			if(TextUtils.isEmpty(absolutePath)){
				return null;
			}
			File file = new File(absolutePath);
			if(!file.exists() || file.isDirectory()){
				return null;
			}
			FileInputStream inStream = null;
			try {
				inStream = new FileInputStream(file);
				return inStream;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
			return null;
		}
	
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
