package com.cvte.taobaounion.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.ui.fragment.HomeFragment;
import com.cvte.taobaounion.ui.fragment.RedPacketFragment;
import com.cvte.taobaounion.ui.fragment.SearchFragment;
import com.cvte.taobaounion.ui.fragment.SelectFragment;
import com.cvte.taobaounion.utils.Constant;
import com.cvte.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private SelectFragment mSelectFragment;
    private FragmentManager mFm;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBind = ButterKnife.bind(this);
        initPermission();
        initView();
        initFragments();
        initViewListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    private void initPermission() {
        int networkPermission = checkSelfPermission(Manifest.permission.INTERNET);

        if(networkPermission != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            requestPermissions(new String[]{Manifest.permission.INTERNET}, Constant.PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode== Constant.PERMISSION_REQUEST_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                Log.d(TAG,"has permissions..");
                //有权限
            } else {
                Log.d(TAG,"no permissionS...");
                //没权限
                finish();
            }
        }
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

    private BaseFragment lastOneFragment = null;

    private void switchFragment(BaseFragment baseFragment) {
        /*通过add hide控制fragement的切换，replace会重新创建*/
        //开启事务
        FragmentTransaction transaction = mFm.beginTransaction();
        if (!baseFragment.isAdded()) {
            transaction.add(R.id.main_page_container,baseFragment);
        } else {
            transaction.show(baseFragment);
        }
        if (lastOneFragment != null) {
            transaction.hide(lastOneFragment);
        }
        lastOneFragment = baseFragment;
        //transaction.replace(R.id.main_page_container,baseFragment);
        transaction.commit();
    }

    private void initView() {
        mNavigationView = this.findViewById(R.id.main_navigation_bar);

    }
}
