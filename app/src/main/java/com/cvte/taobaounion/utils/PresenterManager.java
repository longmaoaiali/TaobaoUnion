package com.cvte.taobaounion.utils;

import com.cvte.taobaounion.presenter.impl.CategoryPagerPresenterImpl;
import com.cvte.taobaounion.presenter.impl.HomePresenterImpl;
import com.cvte.taobaounion.presenter.impl.TicketPresenterImpl;

/**
 * Created by user on 2020/11/3.
 */

public class PresenterManager {

    private static final PresenterManager instance = new PresenterManager();

    public HomePresenterImpl getHomePresenter() {
        return mHomePresenter;
    }

    public CategoryPagerPresenterImpl getCategoryPagerPresenter() {
        return mCategoryPagerPresenter;
    }

    private final HomePresenterImpl mHomePresenter;
    private final CategoryPagerPresenterImpl mCategoryPagerPresenter;

    public TicketPresenterImpl getTicketPresenter() {
        return mTicketPresenter;
    }

    private final TicketPresenterImpl mTicketPresenter;

    public static PresenterManager getInstance() {
        return instance;
    }

    private PresenterManager(){
        mHomePresenter = new HomePresenterImpl();
        mCategoryPagerPresenter = new CategoryPagerPresenterImpl();
        mTicketPresenter = new TicketPresenterImpl();
    }
}
