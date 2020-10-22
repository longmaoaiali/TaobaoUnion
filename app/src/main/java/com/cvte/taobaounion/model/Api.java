package com.cvte.taobaounion.model;

import com.cvte.taobaounion.model.domain.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 2020/10/22.
 */

public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();
}
