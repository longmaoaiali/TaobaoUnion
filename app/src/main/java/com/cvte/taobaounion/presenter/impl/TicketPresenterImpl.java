package com.cvte.taobaounion.presenter.impl;

import android.util.Log;

import com.cvte.taobaounion.model.Api;
import com.cvte.taobaounion.model.domain.TicketParams;
import com.cvte.taobaounion.model.domain.TicketResult;
import com.cvte.taobaounion.presenter.ITicketPresenter;
import com.cvte.taobaounion.utils.RetrofitManager;
import com.cvte.taobaounion.utils.UrlUtils;
import com.cvte.taobaounion.view.ITicketPagerCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2020/11/3.
 */

public class TicketPresenterImpl implements ITicketPresenter {

    private static final String TAG = "TicketPresenterImpl";

    @Override
    public void registerViewCallback(ITicketPagerCallback callback) {

    }

    @Override
    public void unregisterViewCallback(ITicketPagerCallback callback) {

    }

    @Override
    public void getTicket(String title, String url, String cover) {
        /*获取淘口令*/
        //url作为post请求的参数，需要确保URL正确
        String ticketUrl = UrlUtils.getTicketUrl(url);

        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        TicketParams ticketParams = new TicketParams(title,ticketUrl);
        Call<TicketResult> task = api.getTicket(ticketParams);
        task.enqueue(new Callback<TicketResult>() {
            @Override
            public void onResponse(Call<TicketResult> call, Response<TicketResult> response) {
                int code = response.code();
                Log.d(TAG,"getTicket--> response code "+code);
            }

            @Override
            public void onFailure(Call<TicketResult> call, Throwable t) {
                Log.d(TAG,"getTicket--> error msg "+t);
            }
        });
    }
}
