package com.cvte.taobaounion.ui.fragment;

import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;

/**
 * Created by user on 2020/10/23.
 */

public class HomePagerFragment extends BaseFragment{
    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
