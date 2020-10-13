package com.lib.marketplace.holder;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.bussiness.FontBussiness;


public abstract class AbsRecyleHolder extends RecyclerView.ViewHolder {

    protected Context mContext;
    protected FontBussiness mFontBussiness;
    protected Resources mResources;
    private View mConvertView;


    public AbsRecyleHolder(Context mContext, View view) {
        super(view);
        mConvertView = view;
        this.mContext = mContext;
        this.mResources = mContext.getResources();
        ApplicationIpos app = (ApplicationIpos) mContext.getApplicationContext();
        mFontBussiness = app.getFontBussiness();
    }


    public static int getItemLayout(int mType) {
        return 0;
    }

    public View getConvertView() {
        return mConvertView;
    }


    public abstract void setElement(Object obj);


}
