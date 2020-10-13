package com.lib.marketplace.holder.marketplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lib.marketplace.model.marketplace.NvlModel;
import com.lib.marketplace.R;
import com.lib.marketplace.holder.AbsRecyleHolder;
import com.lib.marketplace.util.FormatNumberUtil;

public class NvlHolder extends AbsRecyleHolder {

    private SimpleDraweeView mImage;
    private TextView mName;
    private TextView mQuantity;
    private ImageView mPlus;
    private ImageView mSub;
    private TextView mPrice;
    private TextView mSubTitle;
    private ImageView mAdd;
    private View mView;

    private NvlModel dmNvlModel;
    private NvlHolder.OnItemClickRycle mOnItemClickRycle;


    public NvlHolder(Context mContext, View view, NvlHolder.OnItemClickRycle mOnItemClickRycle) {
        super(mContext, view);
        this.mOnItemClickRycle = mOnItemClickRycle;
        findViewd(getConvertView());

    }

    public static NvlHolder newInstance(Context mContext, LayoutInflater inflater, NvlHolder.OnItemClickRycle mOnItemClickRycle) {
        View convertView = inflater.inflate(getItemLayout(), null);
        NvlHolder mMenuFoodItemRecycleHolder = new NvlHolder(mContext, convertView, mOnItemClickRycle);
        return mMenuFoodItemRecycleHolder;
    }

    private static int getItemLayout() {
        return R.layout.adapter_service_list;
    }

    private void findViewd(View convertView) {
        mImage = convertView.findViewById(R.id.image);
        mName = convertView.findViewById(R.id.title);
        mQuantity = convertView.findViewById(R.id.quantity);
        mPlus = convertView.findViewById(R.id.up);
        mSub = convertView.findViewById(R.id.down);
        mPrice = convertView.findViewById(R.id.total_price);
        mSubTitle = convertView.findViewById(R.id.adress);
        mAdd = convertView.findViewById(R.id.plus);
        mView = convertView.findViewById(R.id.mView);

        mView.setOnClickListener(v -> {
            mOnItemClickRycle.onClickDes(dmNvlModel);
        });

        mAdd.setOnClickListener(view -> {
            dmNvlModel.setQuantity(1);

            mOnItemClickRycle.onItemChoice(dmNvlModel);
        });


        mPlus.setOnClickListener(v -> {
            int quantity = dmNvlModel.getQuantity();
            quantity++;
            dmNvlModel.setQuantity(quantity);
//            Log.i(TAG, "Quantity: " + mDmServiceListOrigin.getQuantity());
            mQuantity.setText(FormatNumberUtil.fmt(dmNvlModel.getQuantity()));
            mOnItemClickRycle.onItemChoice(dmNvlModel);
        });

        mSub.setOnClickListener(v -> {
            int quantity = dmNvlModel.getQuantity();
            quantity--;
            if (quantity <= 0) {
                quantity = 0;
                hidenLayoutQuantity();
            }
            dmNvlModel.setQuantity(quantity);

            mQuantity.setText(FormatNumberUtil.fmt(dmNvlModel.getQuantity()));
            mOnItemClickRycle.onItemChoice(dmNvlModel);
        });

    }

    @Override
    public void setElement(Object obj) {

        setData((NvlModel) obj);

    }

    private void setData(NvlModel item) {
        this.dmNvlModel = item;

        Glide.with(mContext).load(item.getImage_thumb()).into(mImage);
        mSubTitle.setVisibility(View.INVISIBLE);
        mPrice.setText(FormatNumberUtil.formatCurrency(item.getPrice()));
        mName.setText(item.getName());
        if (item.getQuantity() > 0) {
            hideLayoutQuantity();
        } else {
            hidenLayoutQuantity();
        }
        mQuantity.setText(FormatNumberUtil.fmt(item.getQuantity()));
    }

    private void hideLayoutQuantity() {
        mAdd.setVisibility(View.GONE);
        mSub.setVisibility(View.VISIBLE);
        mQuantity.setVisibility(View.VISIBLE);
        mPlus.setVisibility(View.VISIBLE);
        mSub.setEnabled(true);
        mQuantity.setEnabled(true);
        mPlus.setEnabled(true);
    }

    private void hidenLayoutQuantity() {
        mAdd.setVisibility(View.VISIBLE);
        mSub.setVisibility(View.INVISIBLE);
        mQuantity.setVisibility(View.INVISIBLE);
        mPlus.setVisibility(View.INVISIBLE);
        mSub.setEnabled(false);
        mQuantity.setEnabled(false);
        mPlus.setEnabled(false);
    }

    public interface OnItemClickRycle {
        void onItemChoice(NvlModel dmOrderOnline);

        void onClickDes(NvlModel dmServiceListOrigin);
    }
}

