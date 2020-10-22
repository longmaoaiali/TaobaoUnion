package com.cvte.taobaounion.presenter;

import com.cvte.taobaounion.view.IHomeCallback;

/**
 * Created by user on 2020/10/22.
 */

public interface IHomePresenter {

    /**
     * 获取商品分类
     */
    void getCategories();

    void registerCallback(IHomeCallback callback);

    void unregisterCallback(IHomeCallback callback);

}
