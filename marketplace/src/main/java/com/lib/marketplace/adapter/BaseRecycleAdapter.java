package com.lib.marketplace.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by GiangLV on 8/3/2015.
 */
public abstract class BaseRecycleAdapter extends RecyclerView.ViewHolder {
    protected String TAG = BaseRecycleAdapter.class.getSimpleName();
    protected Context mContext;
    protected Resources mResources;
    private View mConvertView;
    public int lastPosition = -1;

    public BaseRecycleAdapter(Context mContext, View view) {
        super(view);
        mConvertView = view;
        this.mContext = mContext;
        this.mResources = mContext.getResources();
    }


    public static int getItemLayout(int mType) {
        return 0;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
