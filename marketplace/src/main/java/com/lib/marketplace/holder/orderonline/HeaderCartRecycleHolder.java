package com.lib.marketplace.holder.orderonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.marketplace.model.orderonline.DmServiceListOrigin;
import com.lib.marketplace.R;
import com.lib.marketplace.holder.AbsRecyleHolder;


public class HeaderCartRecycleHolder extends AbsRecyleHolder {


    private TextView lblName;


    public HeaderCartRecycleHolder(Context mContext, View view) {
        super(mContext, view);
        findViewd(getConvertView());

    }

    public static HeaderCartRecycleHolder newInstance(Context mContext, LayoutInflater inflater) {
        View convertView = inflater.inflate(getItemLayout(), null);
        HeaderCartRecycleHolder mHistoryOrderHeaderRecycleHolder = new HeaderCartRecycleHolder(mContext, convertView);
        return mHistoryOrderHeaderRecycleHolder;
    }

    private static int getItemLayout() {

        return R.layout.item_header_service_list;


    }

    private void findViewd(View convertView) {

        lblName = convertView.findViewById(R.id.name);

        getConvertView().setTag(this);
    }

    @Override
    public void setElement(Object obj) {

        setData((String) obj);

    }

    private void setData(String item) {
        String name = "";
        if (DmServiceListOrigin.TYPE_SUB.equals(item)) {
            name = mResources.getString(R.string.subscription);
        } else if (DmServiceListOrigin.TYPE_HARDWARE.equals(item)) {
            name = mResources.getString(R.string.hardware);
        } else if (DmServiceListOrigin.TYPE_COMBO.equals(item)) {
            name = mResources.getString(R.string.combo);
        }
        lblName.setText(name);
    }


}
