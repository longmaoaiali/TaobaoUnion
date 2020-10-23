package com.cvte.taobaounion.ui.fragment;

import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;

/**
 * Created by user on 2020/10/21.
 */

public class RedPacketFragment extends BaseFragment {

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_red_packet;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
