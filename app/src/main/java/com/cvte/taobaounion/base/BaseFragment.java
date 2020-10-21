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
        return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootVireResId();
}
