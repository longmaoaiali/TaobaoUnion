package com.cvte.taobaounion.presenter.impl;

import com.cvte.taobaounion.model.Api;
import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.presenter.IHomePresenter;
import com.cvte.taobaounion.utils.LogUtils;
import com.cvte.taobaounion.utils.RetrofitManager;
import com.cvte.taobaounion.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2020/10/22.
 */

public class HomePresenterImpl implements IHomePresenter {
    private static final String TAG = "HomePresenterImpl";

    @Override
    public void getCategories() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                /**
                 * 网络请求数据回来
                 */
                int code = response.code();
                if (code== HttpURLConnection.HTTP_OK) {
                    Categories categories = response.body();
                    LogUtils.d(TAG,categories.toString());
                } else {
                    LogUtils.d(TAG,response.code()+"");

                }

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                LogUtils.d(TAG,"errormsg--> " + t);
            }
        });
    }

    @Override
    public void registerCallback(IHomeCallback callback) {

    }

    @Override
    public void unregisterCallback(IHomeCallback callback) {

    }
}
