package com.cvte.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.model.domain.HomePagerContent;
import com.cvte.taobaounion.presenter.ICategoryPagerPresenter;
import com.cvte.taobaounion.presenter.impl.CategoryPagerPresenterImpl;
import com.cvte.taobaounion.ui.adapter.HomePagerAdapter;
import com.cvte.taobaounion.utils.Constant;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.view.ICategoryCallback;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by user on 2020/10/23.
 */

public class HomePagerFragment extends BaseFragment implements ICategoryCallback {

    public static final String TAG = "HomePagerFragment";
    private ICategoryPagerPresenter mCategoryPagerPresenter;
    private int mMaterialId;

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
        mMaterialId = argument.getInt(Constant.KEY_HOME_PAGE_MATERIAL_ID);
        //Todo:加载数据
        LogUtils.d(TAG,"title--> "+title);
        LogUtils.d(TAG,"materialId--> "+ mMaterialId);
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.getContentByCategoryId(mMaterialId);
        }
    }

    @Override
    protected void initPresenter() {
        mCategoryPagerPresenter = CategoryPagerPresenterImpl.getInstance();
        mCategoryPagerPresenter.registerViewCallback(this);
    }



    @Override
    public void onContentLoaded(List<HomePagerContent.DataBean> contents,int categoryId) {
        if (mMaterialId != categoryId) {
            return;
        }
        //todo:更新UI
        setUpState(State.SUCCESS);
    }

    @Override
    public void onLoading(int categoryId) {
        if (mMaterialId != categoryId) {
            return;
        }
        setUpState(State.LOADING);
    }

    @Override
    public void onError(int categoryId) {
        if (mMaterialId != categoryId) {
            return;
        }
        setUpState(State.ERROR);
    }

    @Override
    public void onEmpty(int categoryId) {
        if (mMaterialId != categoryId) {
            return;
        }
        setUpState(State.EMPTY);
    }

    @Override
    public void onLoadMoreError(int categoryId) {

    }

    @Override
    public void onLoadMoreEmpty(int categoryId) {

    }

    @Override
    public void onLoadMoreLoaded(List<HomePagerContent.DataBean> contents,int categoryId) {

    }

    @Override
    public void onLooperListLoaded(List<HomePagerContent.DataBean> contents) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    private void release() {
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.unregisterViewCallback(this);
        }
    }
}
