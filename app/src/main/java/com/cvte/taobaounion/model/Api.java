package com.cvte.taobaounion.model;

import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.model.domain.HomePagerContent;
import com.cvte.taobaounion.model.domain.TicketParams;
import com.cvte.taobaounion.model.domain.TicketResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by user on 2020/10/22.
 */

public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET
    Call<HomePagerContent> getHomePagerContent(@Url String url);

    @POST("tpwd")
    Call<TicketResult> getTicket(@Body TicketParams ticketParams);
}
