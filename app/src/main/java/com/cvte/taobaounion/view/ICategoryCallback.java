package com.cvte.taobaounion.view;

import com.cvte.taobaounion.model.domain.Categories;
import com.cvte.taobaounion.model.domain.HomePagerContent;

import java.util.List;

/**
 * Created by user on 2020/10/26.
 */

public interface ICategoryCallback {
    /**
     * 数据加载回调
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean> contents);

    /**
     * 加载中
     * @param categoryId
     */
    void onLoading(int categoryId);

    /**
     * 加载错误
     * @param categoryId
     */
    void onError(int categoryId);

    /**
     * 数据为空
     * @param categoryId
     */
    void onEmpty(int categoryId);

    /**
     * 加载更多网络错误
     * @param categoryId
     */
    void onLoadMoreError(int categoryId);

    /**
     * 加载更多数据为空
     * @param categoryId
     */
    void onLoadMoreEmpty(int categoryId);

    /**
     * 加载更多
     * @param contents
     */
    void onLoadMoreLoaded(List<HomePagerContent.DataBean> contents);

    /**
     * 轮播图
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean> contents);


}
