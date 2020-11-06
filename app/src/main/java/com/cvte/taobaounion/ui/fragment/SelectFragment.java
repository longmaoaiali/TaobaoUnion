package com.cvte.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.SelectedContent;
import com.cvte.taobaounion.model.domain.SelectedPageCategory;
import com.cvte.taobaounion.presenter.ISelectedPagePresenter;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.view.ISelectedPageCallback;

import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * Created by user on 2020/10/21.
 */

public class SelectFragment extends BaseFragment implements ISelectedPageCallback {

    private static final String TAG = "SelectFragment";
    private ISelectedPagePresenter mSelectedPagePresenter;

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mSelectedPagePresenter = PresenterManager.getInstance().getSelectedPagerPresenter();
        mSelectedPagePresenter.registerViewCallback(this);
        mSelectedPagePresenter.getCategories();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSelectedPagePresenter != null) {
            mSelectedPagePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onCategoriesLoaded(SelectedPageCategory categories) {
        LogUtils.d(TAG,"onCategoriesLoaded-->"+categories);
        List<SelectedPageCategory.DataBean> data = categories.getData();
        mSelectedPagePresenter.getContentByCategory(data.get(0));
    }

    @Override
    public void onContentLoaded(SelectedContent content) {
        LogUtils.d(TAG,"onContentLoaded-->");
    }
}
