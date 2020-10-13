package com.lib.marketplace.holder.orderonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.R;
import com.lib.marketplace.holder.AbsRecyleHolder;
import com.lib.marketplace.model.orderonline.DmStatusOrder;

public class StatusOrderHolder extends AbsRecyleHolder {

    private TextView mStatus;
    private View view1;
    private View view2;
    private ImageView mCicle;
    private DmStatusOrder dmStatusOrder;


    public StatusOrderHolder(Context mContext, View view) {
        super(mContext, view);
        findViewd(getConvertView());

    }

    public static StatusOrderHolder newInstance(Context mContext, LayoutInflater inflater) {
        View convertView = inflater.inflate(getItemLayout(), null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(lp);
        StatusOrderHolder mMenuFoodItemRecycleHolder = new StatusOrderHolder(mContext, convertView);
        return mMenuFoodItemRecycleHolder;
    }

    private static int getItemLayout() {
        return R.layout.adapter_status_order;
    }

    private void findViewd(View convertView) {

        mStatus = convertView.findViewById(R.id.status);
        mCicle = convertView.findViewById(R.id.cicle);
        view1 = convertView.findViewById(R.id.v1);
        view2 = convertView.findViewById(R.id.v2);


    }

    @Override
    public void setElement(Object obj) {

        setData((DmStatusOrder) obj);

    }

    private void setData(DmStatusOrder item) {
        this.dmStatusOrder = item;

        mStatus.setText(dmStatusOrder.getName());
        if (dmStatusOrder.isFirstPosition()) {
            view1.setVisibility(View.INVISIBLE);
        } else {
            view1.setVisibility(View.VISIBLE);
        }

        if (dmStatusOrder.isEndPosition()) {
            view2.setVisibility(View.INVISIBLE);
        } else {
            view2.setVisibility(View.VISIBLE);
        }

        if (dmStatusOrder.isCheckedStatus()) {
            mCicle.setImageDrawable(mResources.getDrawable(R.drawable.ic_checked_status));
            view1.setBackgroundColor(mResources.getColor(R.color.mainColor));
            if (dmStatusOrder.isCorectPosition()) {
                view2.setBackgroundColor(mResources.getColor(R.color.E1E1E1));
            } else {
                view2.setBackgroundColor(mResources.getColor(R.color.mainColor));
            }


        } else {
            mCicle.setImageDrawable(mResources.getDrawable(R.drawable.ic_uncheck_status));
            view1.setBackgroundColor(mResources.getColor(R.color.E1E1E1));
            view2.setBackgroundColor(mResources.getColor(R.color.E1E1E1));
        }

    }


}

