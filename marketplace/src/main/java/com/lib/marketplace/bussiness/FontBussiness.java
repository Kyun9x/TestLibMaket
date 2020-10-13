package com.lib.marketplace.bussiness;

import android.graphics.Typeface;

import com.lib.marketplace.app.ApplicationIpos;

public class FontBussiness {

    private Typeface mRobotoLight;
    private Typeface mRobotoBold;
    private Typeface mRobotoRegular;
    private Typeface mRobotoMedium;

    private Typeface mOpenSanLight;
    private Typeface mOpenSanBold;
    private Typeface mOpenSanRegular;
    private Typeface mOpenSanSemiBold;
    private ApplicationIpos mIposManage;

    public FontBussiness(ApplicationIpos mIpos) {
        this.mIposManage = mIpos;
        mRobotoMedium = Typeface.createFromAsset(mIpos.getAssets(),
                "fonts/Roboto-Medium.ttf");
        mRobotoLight = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/Roboto-Light.ttf");
        mRobotoRegular = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/Roboto-Regular.ttf");
        mRobotoBold = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/Roboto-Bold.ttf");


        mOpenSanSemiBold = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/semibold.ttf");
        mOpenSanLight = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/OpenSans-Light.ttf");
        mOpenSanRegular = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/regular.ttf");
        mOpenSanBold = Typeface.createFromAsset(this.mIposManage.getAssets(),
                "fonts/bold.ttf");
    }

    public Typeface getRobotoLight() {
        return mRobotoLight;
    }

    public Typeface getRobotoBold() {
        return mRobotoBold;
    }

    public Typeface getRobotoRegular() {
        return mRobotoRegular;
    }

    public Typeface getRobotoMedium() {
        return mRobotoMedium;
    }

    public Typeface getOpenSanLight() {
        return mOpenSanLight;
    }

    public Typeface getOpenSanBold() {
        return mOpenSanBold;
    }

    public Typeface getOpenSanRegular() {
        return mOpenSanRegular;
    }

    public Typeface getOpenSanSemiBold() {
        return mOpenSanSemiBold;
    }

    public ApplicationIpos getFoodBook() {
        return mIposManage;
    }


}
