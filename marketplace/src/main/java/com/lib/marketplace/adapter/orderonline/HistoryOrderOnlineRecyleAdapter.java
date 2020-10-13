package com.lib.marketplace.adapter.orderonline;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.holder.orderonline.HistoryOrderOnlineRecycleHolder;
import com.lib.marketplace.model.orderonline.DmOrderOnline;

import java.util.ArrayList;

/**
 * Created by NgocLe on 10/02/2019.
 */
public class HistoryOrderOnlineRecyleAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DmOrderOnline> data;
    private LayoutInflater inflater;
    private Activity mActivity;
    private HistoryOrderOnlineRecycleHolder.OnItemClickRycle mOnItemClickRycle;

    public HistoryOrderOnlineRecyleAdapter(Activity mAc, ArrayList<DmOrderOnline> data, HistoryOrderOnlineRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        this.mActivity = mAc;
        this.data = data;
        this.mOnItemClickRycle = mOnItemClickRycle;
        inflater = (LayoutInflater) mAc
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HistoryOrderOnlineRecycleHolder.newInstance(mActivity, inflater, mOnItemClickRycle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryOrderOnlineRecycleHolder mHolder = (HistoryOrderOnlineRecycleHolder) holder;
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
