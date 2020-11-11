package com.cvte.taobaounion.presenter.impl;

import com.cvte.taobaounion.model.Api;
import com.cvte.taobaounion.model.domain.SearchRecommand;
import com.cvte.taobaounion.model.domain.SearchResult;
import com.cvte.taobaounion.presenter.ISearchPresenter;
import com.cvte.taobaounion.utils.RetrofitManager;
import com.cvte.taobaounion.view.ISearchViewCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2020/11/11.
 */

public class SearchPresenterImpl implements ISearchPresenter{


    private final Api mApi;
    private ISearchViewCallback mSearchViewCallback = null;

    public static final int DEFAULT_PAGE = 1;

    private int mCurrentPage = DEFAULT_PAGE;
    private String mCurrentSearchKeyword = null;

    public SearchPresenterImpl(){
        RetrofitManager instance = RetrofitManager.getInstance();
        Retrofit retrofit = instance.getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public void registerViewCallback(ISearchViewCallback callback) {
        this.mSearchViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISearchViewCallback callback) {
        this.mSearchViewCallback = null;
    }

    @Override
    public void getHistories() {

    }

    @Override
    public void delHistories() {

    }

    @Override
    public void doSearch(String keyword) {
        this.mCurrentSearchKeyword = keyword;

        if (mSearchViewCallback != null) {
            mSearchViewCallback.onLoading();
        }

        Call<SearchResult> task = mApi.doSearch(mCurrentPage, keyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                int code = response.code();
                if(code== HttpURLConnection.HTTP_OK){
                    handleSearchResult(response.body());
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                t.printStackTrace();
                onError();
            }
        });
    }

    private void onError() {
        if (mSearchViewCallback != null) {
            mSearchViewCallback.onNetworkError();
        }
    }

    private void handleSearchResult(SearchResult result) {
        if (mSearchViewCallback != null) {
            if (isResultEmpty(result)) {
                mSearchViewCallback.onEmpty();
            } else {
                mSearchViewCallback.onSearchSuccess(result);
            }
        }
    }

    /**
     * 返回true 代表数据不可用
     * @param result
     * @return
     */
    private boolean isResultEmpty(SearchResult result) {
        try {
            return  result == null || result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().size() == 0;
        } catch (Exception e){
            return true;
        }
    }

    @Override
    public void research() {
        if (mSearchViewCallback != null) {
            if (mCurrentSearchKeyword == null) {
                mSearchViewCallback.onEmpty();
            } else {
                this.doSearch(mCurrentSearchKeyword);
            }
        }
    }

    @Override
    public void loaderMore() {

    }

    @Override
    public void getRecommendWords() {
        Call<SearchRecommand> task = mApi.getRecommendWorlds();
        task.enqueue(new Callback<SearchRecommand>() {
            @Override
            public void onResponse(Call<SearchRecommand> call, Response<SearchRecommand> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    if (mSearchViewCallback != null) {
                        mSearchViewCallback.onRecommendWordsLoaded(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchRecommand> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
