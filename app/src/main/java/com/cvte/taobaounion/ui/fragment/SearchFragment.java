package com.cvte.taobaounion.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.Histories;
import com.cvte.taobaounion.model.domain.SearchRecommand;
import com.cvte.taobaounion.model.domain.SearchResult;
import com.cvte.taobaounion.presenter.ISearchPresenter;
import com.cvte.taobaounion.presenter.impl.TicketPresenterImpl;
import com.cvte.taobaounion.ui.activity.TicketActivity;
import com.cvte.taobaounion.ui.adapter.SearchAdapter;
import com.cvte.taobaounion.ui.custom.TextFlowLayout;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.utils.SizeUtils;
import com.cvte.taobaounion.view.ISearchViewCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by user on 2020/10/21.
 */

public class SearchFragment extends BaseFragment implements ISearchViewCallback {

    private ISearchPresenter mSearchPresenter;
    private static final String TAG = "SearchFragment";

    @BindView(R.id.search_history_view)
    public TextFlowLayout mTextFlowLayoutHistory;

    @BindView(R.id.search_recommend_view)
    public TextFlowLayout mTextFlowLayoutRecommend;

    @BindView(R.id.search_recommend_container)
    public View mRecommendContainer;

    @BindView(R.id.search_history_container)
    public View mHistoryContainer;

    @BindView(R.id.search_history_delete)
    public View mHistoryDelete;

    @BindView(R.id.search_result_list)
    public RecyclerView mResultListView;

    @BindView(R.id.search_btn)
    public TextView mSearchBtn;

    @BindView(R.id.search_clean_btn)
    public ImageView mCleanInputBtn;

    @BindView(R.id.search_input_box)
    public EditText mSearchInputBox;




    private SearchAdapter mSearchAdapter;


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
    protected void initViewListener() {
        super.initViewListener();

        mSearchInputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogUtils.d(TAG,"onEditorAction-->");
                if (actionId == EditorInfo.IME_ACTION_SEARCH && mSearchPresenter != null) {
                    String keyword = v.getText().toString().trim();
                    LogUtils.d(TAG,"onEditorAction-->keyword"+keyword);
                    if (TextUtils.isEmpty(keyword)) {
                        return  false;
                    }
                    LogUtils.d(TAG,"onEditorAction-->doSearch(keyword) " + keyword);
                    mSearchPresenter.doSearch(keyword);
                }
                return false;
            }
        });

        mSearchAdapter.setOnListItemClickListener(new SearchAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(SearchResult.DataBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean item) {
                String title = item.getTitle();
                String url = item.getCoupon_share_url();
                if (TextUtils.isEmpty(url)) {
                    url = item.getPict_url();
                }
                String cover = item.getPict_url();

                TicketPresenterImpl mTicketPresenter = PresenterManager.getInstance().getTicketPresenter();
                mTicketPresenter.getTicket(title,url,cover);
                startActivity(new Intent(getContext(), TicketActivity.class));
            }
        });

        mHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchPresenter != null) {
                    mSearchPresenter.delHistories();
                }
            }
        });
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
        mResultListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSearchAdapter = new SearchAdapter();
        mResultListView.setAdapter(mSearchAdapter);
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
    public void onHistoriesLoaded(Histories histories) {
        //LogUtils.d(TAG,"onHistoriesLoaded-->"+histories.getHistories().get(0));
        if (histories == null || histories.getHistories().size() == 0) {
            mHistoryContainer.setVisibility(View.GONE);
        } else {
            mHistoryContainer.setVisibility(View.VISIBLE);
            mTextFlowLayoutHistory.setTextList(histories.getHistories());
        }
    }

    @Override
    public void onHistoriesDeleted() {
        if (mSearchPresenter != null) {
            //更新UI显示
            mSearchPresenter.getHistories();
        }
    }

    @Override
    public void onSearchSuccess(SearchResult result) {
        LogUtils.d(TAG,"search result --> "+result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().get(0).getCoupon_info());
        //mSearchAdapter
        /*隐藏历史记录和推荐 显示搜索结果*/
        mRecommendContainer.setVisibility(View.GONE);
        mHistoryContainer.setVisibility(View.GONE);
        mResultListView.setVisibility(View.VISIBLE);

        mSearchAdapter.setData(result);
        mResultListView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(),1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(),1.5f);


            }
        });

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

        if (recommendList==null || recommendList.size()==0) {
            mRecommendContainer.setVisibility(View.GONE);
        } else {
            mRecommendContainer.setVisibility(View.VISIBLE);
            mTextFlowLayoutRecommend.setTextList(recommendList);
        }

    }
}
