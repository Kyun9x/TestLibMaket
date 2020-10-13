package com.lib.marketplace.adapter.marketplace;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.holder.marketplace.NvlHolder;
import com.lib.marketplace.model.marketplace.NvlModel;

import java.util.ArrayList;

/**
 * Created by NgocLe on 10/02/2019.
 */
public class NvlRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NvlModel> data;
    private LayoutInflater inflater;
    private Activity mActivity;
    private NvlHolder.OnItemClickRycle mOnItemClickRycle;

    public NvlRecyclerViewAdapter(Activity mAc, ArrayList<NvlModel> data, NvlHolder.OnItemClickRycle mOnItemClickRycle) {
        this.mActivity = mAc;
        this.data = data;
        this.mOnItemClickRycle = mOnItemClickRycle;
        inflater = (LayoutInflater) mAc
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NvlHolder.newInstance(mActivity, inflater, mOnItemClickRycle);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NvlHolder mHolder = (NvlHolder) holder;
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
