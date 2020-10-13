package com.lib.marketplace.holder.marketplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.lib.marketplace.model.marketplace.NvlOnlineModel;
import com.ipos.iposmanage.util.DateTimeUtil;
import com.lib.marketplace.R;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.holder.AbsRecyleHolder;
import com.lib.marketplace.util.FormatNumberUtil;

public class HistoryNvlRecycleHolder extends AbsRecyleHolder {

    private TextView mTitle;
    private TextView mTime;
    private TextView mStatus;
    private TextView mPrice;
    private NvlOnlineModel dmOrderOnline;
    private TextView mAdress;
    private HistoryNvlRecycleHolder.OnItemClickRycle mOnItemClickRycle;

    public HistoryNvlRecycleHolder(Context mContext, View view, HistoryNvlRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        super(mContext, view);
        this.mOnItemClickRycle = mOnItemClickRycle;
        findViewd(getConvertView());
    }

    public static HistoryNvlRecycleHolder newInstance(Context mContext, LayoutInflater inflater, HistoryNvlRecycleHolder.OnItemClickRycle mOnItemClickRycle) {
        View convertView = inflater.inflate(getItemLayout(), null);
        HistoryNvlRecycleHolder mMenuFoodItemRecycleHolder = new HistoryNvlRecycleHolder(mContext, convertView, mOnItemClickRycle);
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
        setData((NvlOnlineModel) obj);
    }

    private void setData(NvlOnlineModel item) {
        this.dmOrderOnline = item;
        mTitle.setText("#" + dmOrderOnline.getInvoice_id());
        if (dmOrderOnline.getLocation() != null) {
            String nguoinhan = mContext.getString(R.string.nguoi_nhan) + ": " + item.getRecipient_name() + " - " + item.getRecipient_id() + "\n" +
                    mContext.getString(R.string.dia_chi_nhan_hang) + ": " + dmOrderOnline.getLocation().getLocation_name();
            mAdress.setText(nguoinhan);
        }
        mPrice.setText(FormatNumberUtil.formatCurrency(dmOrderOnline.getFinal_amount()));
        if(dmOrderOnline.getStatus() == Constants.OrderNvlStatus.COMPLETED){
            mStatus.setText(mContext.getString(R.string.hoan_thanh));
        }else if(dmOrderOnline.getStatus() == Constants.OrderNvlStatus.CANCELED){
            mStatus.setText(mContext.getString(R.string.da_huy));
        }else if(dmOrderOnline.getStatus().equals(Constants.OrderNvlStatus.PENDING)){
            mStatus.setText(mContext.getString(R.string.cho_xy_ly));
        }else if(dmOrderOnline.getStatus() == Constants.OrderNvlStatus.CONFIRMED){
            mStatus.setText(mContext.getString(R.string.da_xac_nhan));
        }
        mTime.setText(DateTimeUtil.convertTimeStampToDate(dmOrderOnline.getCreated_at(),
                Constants.Date.Format.HH_MM_DD_MM_YYYY
        ));
    }

    public interface OnItemClickRycle {
        void onItemChoice(NvlOnlineModel dmOrderOnline);
    }
}

