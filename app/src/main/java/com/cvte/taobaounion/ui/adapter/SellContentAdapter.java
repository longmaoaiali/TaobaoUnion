package com.cvte.taobaounion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvte.taobaounion.R;
import com.cvte.taobaounion.model.domain.SellContent;
import com.cvte.taobaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by user on 2020/11/10.
 */

public class SellContentAdapter extends RecyclerView.Adapter<SellContentAdapter.InnerHolder> {


    private List<SellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> mMapData = new ArrayList<>();
    private static final String TAG = "SellContentAdapter";

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMapData.size();
    }

    public void setData(SellContent result) {
        this.mMapData.clear();
        LogUtils.d(TAG,"sell result-->"+result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data().size());
        this.mMapData.addAll(result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }
}
