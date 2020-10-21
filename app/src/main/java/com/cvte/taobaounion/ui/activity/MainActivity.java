package com.cvte.taobaounion.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.ui.fragment.HomeFragment;
import com.cvte.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initViewListener();
    }

    private void initViewListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                LogUtils.d(TAG,"item title-->" + item.getTitle()+"item id"+item.getItemId());
                if (item.getItemId() == R.id.home) {
                    LogUtils.d(TAG,"切换在首页");
                } else if (item.getItemId() == R.id.selected){
                    LogUtils.d(TAG,"切换在精选");
                } else if (item.getItemId() == R.id.red_packet){
                    LogUtils.d(TAG,"切换在特惠");
                } else if (item.getItemId() == R.id.search) {
                    LogUtils.d(TAG,"切换在搜索");
                }

                return true;
            }
        });
    }

    private void initView() {
        mNavigationView = this.findViewById(R.id.main_navigation_bar);


        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.main_page_container,homeFragment);
        transaction.commit();
    }
}
