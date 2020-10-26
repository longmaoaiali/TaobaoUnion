package com.cvte.taobaounion.presenter.impl;

import com.cvte.taobaounion.model.Api;
import com.cvte.taobaounion.model.domain.HomePagerContent;
import com.cvte.taobaounion.presenter.ICategoryPagerPresenter;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.RetrofitManager;
import com.cvte.taobaounion.utils.UrlUtils;
import com.cvte.taobaounion.view.ICategoryCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by user on 2020/10/26.
 */

public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {

    private CategoryPagerPresenterImpl(){

    }

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getInstance(){
        if (sInstance == null) {
            sInstance = new CategoryPagerPresenterImpl();
        }
        return sInstance;
    }

    private Map<Integer,Integer> pagesInfo = new HashMap<>();
    private static final int DEFAULT_PAGE = 1;
    private static final String TAG = "CategoryPagerPresenterImpl";

    @Override
    public void registerViewCallback(ICategoryCallback callback) {

    }

    @Override
    public void unregisterViewCallback(ICategoryCallback callback) {

    }

    @Override
    public void getContentByCategoryId(int categoryId) {
        /*根据分类ID加载内容*/
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Integer targetPage = pagesInfo.get(categoryId);
        if (targetPage == null) {
            pagesInfo.put(categoryId,DEFAULT_PAGE);
            targetPage = DEFAULT_PAGE;
        }
        String homePagerUrl = UrlUtils.createHomePagerUrl(categoryId,targetPage);
        LogUtils.d(TAG,"homePagerUrl --> "+homePagerUrl);
        Call<HomePagerContent> task = api.getHomePagerContent(homePagerUrl);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                int code = response.code();
                LogUtils.d(TAG,"response code-->"+code);
                if (code==HTTP_OK) {
                    HomePagerContent pagerContent = response.body();
                    LogUtils.d(TAG,"pagerContent --> "+pagerContent);
                } else {
                    //TODO:
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.d(TAG,"onFailure--> "+t.toString());
            }
        });
    }

    @Override
    public void loadMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }
}
