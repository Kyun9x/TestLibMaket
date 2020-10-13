package com.lib.marketplace.adapter.orderonline;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.holder.orderonline.OrderDetailRecycleHolder;
import com.lib.marketplace.model.orderonline.DmService;

import java.util.ArrayList;

/**
 * Created by NgocLe on 10/02/2019.
 */
public class OrderDetailRecyleAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DmService> data;
    private LayoutInflater inflater;
    private Activity mActivity;


    public OrderDetailRecyleAdapter(Activity mAc, ArrayList<DmService> data) {
        this.mActivity = mAc;
        this.data = data;
        inflater = (LayoutInflater) mAc
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OrderDetailRecycleHolder.newInstance(mActivity, inflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderDetailRecycleHolder mHolder = (OrderDetailRecycleHolder) holder;
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
