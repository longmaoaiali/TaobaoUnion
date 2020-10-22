package com.cvte.taobaounion.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.ui.fragment.HomeFragment;
import com.cvte.taobaounion.ui.fragment.RedPacketFragment;
import com.cvte.taobaounion.ui.fragment.SearchFragment;
import com.cvte.taobaounion.ui.fragment.SelectFragment;
import com.cvte.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private SelectFragment mSelectFragment;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragments();
        initViewListener();
    }

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();
        mSelectFragment = new SelectFragment();
        mFm = getSupportFragmentManager();

        switchFragment(mHomeFragment);
    }

    private void initViewListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                LogUtils.d(TAG,"item title-->" + item.getTitle()+"item id"+item.getItemId());
                if (item.getItemId() == R.id.home) {
                    switchFragment(mHomeFragment);
                    LogUtils.d(TAG,"切换在首页");
                } else if (item.getItemId() == R.id.selected){
                    switchFragment(mSelectFragment);
                    LogUtils.d(TAG,"切换在精选");
                } else if (item.getItemId() == R.id.red_packet){
                    switchFragment(mRedPacketFragment);
                    LogUtils.d(TAG,"切换在特惠");
                } else if (item.getItemId() == R.id.search) {
                    switchFragment(mSearchFragment);
                    LogUtils.d(TAG,"切换在搜索");
                }

                return true;
            }
        });
    }

    private void switchFragment(BaseFragment baseFragment) {
        //开启事务
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.replace(R.id.main_page_container,baseFragment);
        transaction.commit();
    }

    private void initView() {
        mNavigationView = this.findViewById(R.id.main_navigation_bar);

    }
}
