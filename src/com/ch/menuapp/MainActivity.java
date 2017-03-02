package com.ch.menuapp;

import java.util.ArrayList;
import java.util.List;

import com.ch.entity.Product;
import com.ch.model.LeftAdapter;
import com.ch.model.RightAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.leftListView = (ListView) findViewById(R.id.left);
        this.rightListView = (ListView) findViewById(R.id.right);
       
        for(int i=0;i<100;i++){
        	Product product = new Product();
        	product.nameString = "ceshi";
        	this.mProductList.add(product);
        }
        rightAdapter = new RightAdapter(this, this.mProductList);
        leftAdapter = new LeftAdapter(this, this.mProductList);
        Log.d("TAG","mproduct:"+this.mProductList.size());
        this.leftListView.setAdapter(leftAdapter);
        this.rightListView.setAdapter(rightAdapter);
    }
    public final static class ViewHolder {
        TextView tvTag;
        TextView tvSection;
    }
    
    private ListView rightListView;          //右侧商品listview
    private ListView leftListView;   //左侧--商品类型listview
    private RightAdapter rightAdapter;   //右侧adapter
    private LeftAdapter leftAdapter;  //左侧adapter
    private ImageView shopCart;//购物车
    private View cartFrame;
    private TextView buyNumView;//购物车上的数量标签
    private TextView mPriceSumView;
    //private View cartView;
    private TextView selectedView;
    private View titleLayout;
    private TextView title;
    private ListView popuListView;
    
    private List<Product> mProductList = new ArrayList<Product>();
    //private List<Cart> mCartList = new ArrayList<Cart>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
