package com.cvte.taobaounion.ui.fragment;

import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.SearchRecommand;
import com.cvte.taobaounion.model.domain.SearchResult;
import com.cvte.taobaounion.presenter.ISearchPresenter;
import com.cvte.taobaounion.ui.custom.TextFlowLayout;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.view.ISearchViewCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by user on 2020/10/21.
 */

public class SearchFragment extends BaseFragment implements ISearchViewCallback {

    private ISearchPresenter mSearchPresenter;
    private static final String TAG = "SearchFragment";

    @BindView(R.id.search_history_view)
    public TextFlowLayout mTextFlowLayout;

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initPresenter() {
        mSearchPresenter = PresenterManager.getInstance().getSearchPresenter();
        mSearchPresenter.registerViewCallback(this);
        /*获取搜索推荐词*/
        mSearchPresenter.getRecommendWords();
        mSearchPresenter.doSearch("毛衣");
        mSearchPresenter.getHistories();
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSearchPresenter != null) {
            mSearchPresenter.unregisterViewCallback(this);
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
    public void onHistoriesLoaded(List<String> histories) {
        LogUtils.d(TAG,"onHistoriesLoaded-->"+histories.get(0));
    }

    @Override
    public void onHistoriesDeleted() {

    }

    @Override
    public void onSearchSuccess(SearchResult result) {
        LogUtils.d(TAG,"search result --> "+result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().get(0).getCoupon_info());
    }

    @Override
    public void onMoreLoaded(SearchResult result) {

    }

    @Override
    public void onMoreLoadedError() {

    }

    @Override
    public void onMoreLoadedEmpty() {

    }

    @Override
    public void onRecommendWordsLoaded(List<SearchRecommand.DataBean> recommendWoeds) {
        //推荐词回调
        LogUtils.d(TAG,"size--> "+recommendWoeds.size());
        List<String> recommendList = new ArrayList<>();
        for (SearchRecommand.DataBean recommendWoed : recommendWoeds) {
            recommendList.add(recommendWoed.getKeyword());
        }

        mTextFlowLayout.setTextList(recommendList);
    }
}
