package com.ch.entity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


public class BaseActivity extends Activity {
	public static List<WeakReference<BaseActivity>> baseActivityList = new ArrayList<WeakReference<BaseActivity>>();  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        WeakReference<BaseActivity> ba = new WeakReference<BaseActivity>(this);  
        baseActivityList.add(ba);  
    }  
    @Override  
    public void setContentView(int layoutResID) {  
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);  
        setContentView(contentView);  
    }  
  
    @Override  
    public void setContentView(View view) {  
        // TODO Auto-generated method stub  
        setContentView(view,null);  
    }  
    @Override  
    public void setContentView(View view,ViewGroup.LayoutParams lp) {  
        boolean hasTitle = hasTitle();  
        if(hasTitle){  
            int titleLayoutId = 0;//getCustomTitleLayoutId();  
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
            if(lp == null){  
                super.setContentView(view);  
            }else{  
                super.setContentView(view, lp);  
            }  
            Window w = getWindow();  
            w.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, titleLayoutId);  
            Drawable titleBg = getTitleBg();  
            if(titleBg != null){  
//                ViewGroup titleGroup = (ViewGroup)findViewById(R.id.title_root_group);  
//                titleGroup.setBackgroundDrawable(titleBg);  
            }  
              
            initTitle();  
        }else{  
            requestWindowFeature(Window.FEATURE_NO_TITLE);  
            if(lp == null){  
                super.setContentView(view);  
            }else{  
                super.setContentView(view, lp);  
            }  
        }  
          
    }  
  
    /** 
     * 初始化View，分三部分： 
     * Left、Center、Right：可以设置图片、文字、点击事件 
     */  
    private void initTitle(){  
//        titleLeftTV = (TextView)findViewById(R.id.title_left_tv);  
//        titleLeftIV = (ImageView)findViewById(R.id.title_left_iv);  
//        titleNameTV = (TextView)findViewById(R.id.title_name_tv);  
//        titleRightTV = (TextView)findViewById(R.id.title_right_tv);  
//        titleRightIV = (ImageView)findViewById(R.id.title_right_iv);  
////下面三个方法根据自己的要求实现；  
//        setTitleLeftView();  
//        setTitleNameView();  
//        setTitleRightView();  
    }  
      
    /** 
     * 是否显示Title，子类可以重写； 
     * @return 
     */  
    protected boolean hasTitle(){  
        return true;  
    }  
      
    /** 
     * Title布局文件； 
     * @return 
     */  
//    protected int getCustomTitleLayoutId(){  
//        return R.layout.custom_common_title;  
//    }  
      
    /** 
     * Title背景，子类可以重写; 
     * @return 
     */  
    protected Drawable getTitleBg(){  
        return null;  
    }  
      
      
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        for (int i = 0; i < baseActivityList.size(); i++) {  
            WeakReference<BaseActivity> ba = baseActivityList.get(i);  
            if (ba != null && null != ba.get() && ba.get() == this) {  
                baseActivityList.remove(i);  
            }  
        }  
    }  
}
