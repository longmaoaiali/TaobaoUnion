package com.cvte.taobaounion.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cvte.taobaounion.R;
import com.cvte.taobaounion.base.BaseActivity;
import com.cvte.taobaounion.model.domain.TicketResult;
import com.cvte.taobaounion.presenter.ITicketPresenter;
import com.cvte.taobaounion.utils.PresenterManager;
import com.cvte.taobaounion.utils.UrlUtils;
import com.cvte.taobaounion.view.ITicketPagerCallback;

import butterknife.BindView;

/**
 * Created by user on 2020/11/3.
 */

public class TicketActivity extends BaseActivity implements ITicketPagerCallback {

    private static final String TAG = "TicketActivity";
    private ITicketPresenter mTicketPresenter;

    @BindView(R.id.ticket_cover)
    public ImageView mTicketCover;

    @BindView(R.id.ticket_code)
    public EditText mTicketCode;

    @BindView(R.id.ticket_copy_btn)
    public TextView mTicketCopyBtn;

    @BindView(R.id.ticket_press_back)
    public ImageView mTicketPressBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
        initViewListener();
    }

    private void initViewListener() {
        mTicketPressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {

    }

    private void initPresenter() {
        mTicketPresenter = PresenterManager.getInstance().getTicketPresenter();
        if (mTicketPresenter != null) {
            mTicketPresenter.registerViewCallback(this);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTicketPresenter != null) {
            mTicketPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onTicketLoaded(String cover, TicketResult result) {
        Log.d(TAG,"cover url--> " + cover);
        if (mTicketCover != null && !TextUtils.isEmpty(cover)) {
            String coverPath = UrlUtils.getCoverPath(cover);
            Log.d(TAG,"coverPath url--> " + coverPath);
            Glide.with(this).load(coverPath).into(mTicketCover);
        }

        if(result.getData().getTbk_tpwd_create_response()!=null){
            mTicketCode.setText(result.getData().getTbk_tpwd_create_response().getData().getModel());
        }
    }
}
