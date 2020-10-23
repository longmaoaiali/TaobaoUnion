package com.cvte.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;

import androidx.fragment.app.Fragment;

/**
 * Created by user on 2020/10/21.
 */

public class SelectFragment extends BaseFragment {

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
