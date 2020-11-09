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
import com.cvte.taobaounion.ui.adapter.SelectedPageLeftAdapter;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.view.ISelectedPageCallback;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by user on 2020/10/21.
 */

public class SelectFragment extends BaseFragment implements ISelectedPageCallback {

    private static final String TAG = "SelectFragment";
    private ISelectedPagePresenter mSelectedPagePresenter;

    @BindView(R.id.left_category_list)
    public RecyclerView leftCategoryList;

    @BindView(R.id.right_category_content)
    public RecyclerView rightCategoryContentList;
    private SelectedPageLeftAdapter mSelectedPageLeftAdapter;

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
        leftCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mSelectedPageLeftAdapter = new SelectedPageLeftAdapter();

        leftCategoryList.setAdapter(mSelectedPageLeftAdapter);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mSelectedPagePresenter = PresenterManager.getInstance().getSelectedPagerPresenter();
        mSelectedPagePresenter.registerViewCallback(this);
        mSelectedPagePresenter.getCategories();
    }

    @Override
    protected void initViewListener() {
        super.initViewListener();
        mSelectedPageLeftAdapter.setOnLeftItemClickListener(new SelectedPageLeftAdapter.OnLeftItemClickListener() {
            @Override
            public void onLeftItemClick(SelectedPageCategory.DataBean item) {
                //左边分类的点击事件
                LogUtils.d(TAG,"leftRecyclerView  click --> "+item.getFavorites_title());
            }
        });
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
        if (categories != null) {
            mSelectedPageLeftAdapter.setData(categories);
        }
        LogUtils.d(TAG,"onCategoriesLoaded-->"+categories);
        List<SelectedPageCategory.DataBean> data = categories.getData();
        mSelectedPagePresenter.getContentByCategory(data.get(0));
    }

    @Override
    public void onContentLoaded(SelectedContent content) {
        LogUtils.d(TAG,"onContentLoaded-->");
    }
}
