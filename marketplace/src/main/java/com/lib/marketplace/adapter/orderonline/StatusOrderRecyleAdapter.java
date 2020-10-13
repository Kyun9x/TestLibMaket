package com.lib.marketplace.adapter.orderonline;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.holder.orderonline.StatusOrderHolder;
import com.lib.marketplace.model.orderonline.DmStatusOrder;

import java.util.ArrayList;

/**
 * Created by NgocLe on 10/02/2019.
 */
public class StatusOrderRecyleAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DmStatusOrder> data;
    private LayoutInflater inflater;
    private Activity mActivity;


    public StatusOrderRecyleAdapter(Activity mAc, ArrayList<DmStatusOrder> data) {
        this.mActivity = mAc;
        this.data = data;
        inflater = (LayoutInflater) mAc
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StatusOrderHolder.newInstance(mActivity, inflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StatusOrderHolder mHolder = (StatusOrderHolder) holder;
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
