package com.lib.marketplace.adapter.marketplace;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.model.marketplace.NvlOnlineModel;
import com.lib.marketplace.holder.marketplace.HistoryNvlRecycleHolder;

import java.util.ArrayList;

/**
 * Created by NgocLe on 10/02/2019.
 */
public class HistoryNvlRecyleAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NvlOnlineModel> data;
    private LayoutInflater inflater;
    private Activity mActivity;
    private HistoryNvlRecycleHolder.OnItemClickRycle mOnItemClickRycle;

    public HistoryNvlRecyleAdapter(Activity mAc, ArrayList<NvlOnlineModel> data, HistoryNvlRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        this.mActivity = mAc;
        this.data = data;
        this.mOnItemClickRycle = mOnItemClickRycle;
        inflater = (LayoutInflater) mAc
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HistoryNvlRecycleHolder.newInstance(mActivity, inflater, mOnItemClickRycle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryNvlRecycleHolder mHolder = (HistoryNvlRecycleHolder) holder;
        mHolder.setElement(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
