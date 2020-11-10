package com.cvte.taobaounion.ui.fragment;

import android.view.View;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseFragment;
import com.cvte.taobaounion.model.domain.SellContent;
import com.cvte.taobaounion.presenter.IOnSellPagePresenter;
import com.cvte.taobaounion.ui.adapter.SellContentAdapter;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.view.IOnSellPageCallback;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by user on 2020/10/21.
 */

public class RedPacketFragment extends BaseFragment implements IOnSellPageCallback {

    private static final String TAG = "RedPacketFragment";
    private IOnSellPagePresenter mOnSellPagePresenter;
    public static final int DEFAULT_SPAN_COUNT = 2;

    @BindView(R.id.on_sell_content_list)
    public RecyclerView mContentRv;
    private SellContentAdapter mSellContentAdapter;

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mOnSellPagePresenter = PresenterManager.getInstance().getOnSellPagePresenter();
        mOnSellPagePresenter.registerViewCallback(this);
        //网络请求特惠内容的界面
        mOnSellPagePresenter.getOnSellContent();
    }

    @Override
    protected int getRootVireResId() {
        return R.layout.fragment_red_packet;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
        mSellContentAdapter = new SellContentAdapter();
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),DEFAULT_SPAN_COUNT);
        mContentRv.setLayoutManager(gridLayoutManager);
        mContentRv.setAdapter(mSellContentAdapter);

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onContentLoadedSuccess(SellContent result) {
        //todo:update ui
        setUpState(State.SUCCESS);
        LogUtils.d(TAG,"sell result-->"+result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data().size());
        mSellContentAdapter.setData(result);
    }

    @Override
    public void onMoreLoaded(SellContent moreResult) {

    }

    @Override
    public void onMoreLoadedError() {

    }

    @Override
    public void onMoreLoadedEmpty() {

    }
}
