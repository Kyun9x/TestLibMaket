package com.lib.marketplace.holder.orderonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ipos.iposmanage.util.DateTimeUtil;
import com.lib.marketplace.R;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.holder.AbsRecyleHolder;
import com.lib.marketplace.model.orderonline.DmDeliveryInfo;
import com.lib.marketplace.model.orderonline.DmOrderOnline;
import com.lib.marketplace.util.FormatNumberUtil;

public class HistoryOrderOnlineRecycleHolder extends AbsRecyleHolder {

    private TextView mTitle;
    private TextView mTime;
    private TextView mStatus;
    private TextView mPrice;
    private DmOrderOnline dmOrderOnline;
    private TextView mAdress;
    private HistoryOrderOnlineRecycleHolder.OnItemClickRycle mOnItemClickRycle;


    public HistoryOrderOnlineRecycleHolder(Context mContext, View view, HistoryOrderOnlineRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        super(mContext, view);
        this.mOnItemClickRycle = mOnItemClickRycle;
        findViewd(getConvertView());

    }

    public static HistoryOrderOnlineRecycleHolder newInstance(Context mContext, LayoutInflater inflater, HistoryOrderOnlineRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        View convertView = inflater.inflate(getItemLayout(), null);
        HistoryOrderOnlineRecycleHolder mMenuFoodItemRecycleHolder = new HistoryOrderOnlineRecycleHolder(mContext, convertView, mOnItemClickRycle);
        return mMenuFoodItemRecycleHolder;
    }

    private static int getItemLayout() {
        return R.layout.adapter_history_order_online;
    }

    private void findViewd(View convertView) {
        mTitle = convertView.findViewById(R.id.title);
        mTime = convertView.findViewById(R.id.time);
        mStatus = convertView.findViewById(R.id.status);
        mPrice = convertView.findViewById(R.id.price);
        mAdress = convertView.findViewById(R.id.adress);
        getConvertView().setOnClickListener(v -> mOnItemClickRycle.onItemChoice(dmOrderOnline));

    }

    @Override
    public void setElement(Object obj) {

        setData((DmOrderOnline) obj);

    }

    private void setData(DmOrderOnline item) {
        this.dmOrderOnline = item;
        mTitle.setText("#" + dmOrderOnline.getOrderCode());
        DmDeliveryInfo dmDeliveryInfo = dmOrderOnline.getDmDeliveryInfo();
        if (dmDeliveryInfo != null) {
            String nguoinhan = mContext.getString(R.string.nguoi_nhan) + ": " + item.getContactName() + " - " + item.getContactPhone() + "\n" +
                    mContext.getString(R.string.dia_chi_nhan_hang) + ": " + dmDeliveryInfo.getAddress();
            mAdress.setText(nguoinhan);
        }
        mPrice.setText(FormatNumberUtil.formatCurrency(dmOrderOnline.getAmount()));
        mStatus.setText(dmOrderOnline.getStatusName());
        mTime.setText(DateTimeUtil.convertTimeStampToDate(
                DateTimeUtil.convertStringToTimeStamp(
                        item.getCreatedTime(),
                        Constants.Date.Format.YYYY_MM_DD_HH_MM_SS_2
                ),
                Constants.Date.Format.HH_MM_DD_MM_YYYY
        ));
    }

    public interface OnItemClickRycle {

        void onItemChoice(DmOrderOnline dmOrderOnline);

    }


}

