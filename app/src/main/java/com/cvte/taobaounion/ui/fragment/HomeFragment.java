package com.cvte.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.presenter.impl.HomePresenterImpl;
import com.cvte.taobaounion.view.IHomeCallback;

import androidx.fragment.app.Fragment;

/**
 * Created by user on 2020/10/21.
 */

public class HomeFragment extends BaseFragment implements IHomeCallback {


    private HomePresenterImpl mHomePresenter;

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesloaded(Categories categories) {
        //加载的数据回调回来
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    private void release() {
        if (mHomePresenter != null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
