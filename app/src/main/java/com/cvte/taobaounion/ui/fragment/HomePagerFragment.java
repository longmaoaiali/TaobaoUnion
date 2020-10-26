package com.cvte.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.ui.adapter.HomePagerAdapter;
import com.cvte.taobaounion.utils.Constant;
import com.cvte.taobaounion.utils.LogUtils;

import java.security.PublicKey;

/**
 * Created by user on 2020/10/23.
 */

public class HomePagerFragment extends BaseFragment{

    public static final String TAG = "HomePagerFragment";

    public static HomePagerFragment newInstance(Categories.DataBean category) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_HOME_PAGE_TITLE,category.getTitle());
        bundle.putInt(Constant.KEY_HOME_PAGE_MATERIAL_ID,category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void loadData() {
        Bundle argument = getArguments();
        String title = argument.getString(Constant.KEY_HOME_PAGE_TITLE);
        int materialId = argument.getInt(Constant.KEY_HOME_PAGE_MATERIAL_ID);
        //Todo:加载数据
        LogUtils.d(TAG,"title--> "+title);
        LogUtils.d(TAG,"materialId--> "+materialId);
    }
}
