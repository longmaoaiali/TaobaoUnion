package com.cvte.taobaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvte.taobaounion.R;

import androidx.fragment.app.Fragment;

/**
 * Created by user on 2020/10/21.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return loadTootView(inflater,container,savedInstanceState);
    }

    protected  View loadTootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        int resId = getRootVireResId();
        View rootView = inflater.inflate(resId,container,false);

        initPresenter();
        loadData();
        return rootView;
    }

    protected void initPresenter() {

    }

    protected  void loadData(){
        //subClass override
        //并非所有子类都需要loadData 不需要定义抽象方法
    }

    protected abstract int getRootVireResId();
}
