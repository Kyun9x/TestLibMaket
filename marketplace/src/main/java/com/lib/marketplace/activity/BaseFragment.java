package com.lib.marketplace.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lib.marketplace.R;
import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.bussiness.CartBussiness;
import com.lib.marketplace.bussiness.FontBussiness;
import com.lib.marketplace.util.SharedPref;

import ly.count.android.sdk.Countly;


public class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected AppCompatActivity mActivity;
    protected SharedPref pref;
    protected FontBussiness mFontBussiness;
    protected KProgressHUD hud;
    protected CartBussiness mCartBussiness;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
        pref = new SharedPref(mActivity);
        ApplicationIpos app = (ApplicationIpos) mActivity.getApplication();
        mFontBussiness = app.getFontBussiness();
        mCartBussiness = app.getCartBussiness();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getItemLayout(), null);


        return v;
    }

    protected int getItemLayout() {

        return R.layout.fragment_home;
    }

    @Override
    public void onStart() {
        super.onStart();
        Countly.sharedInstance().onStart(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        Countly.sharedInstance().onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Countly.sharedInstance().onConfigurationChanged(newConfig);
    }

    public void showProgressHub(Activity activity) {
        dismissProgressHub();
        hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

        hud.show();
    }

    public void dismissProgressHub() {
        if (hud != null && hud.isShowing())
            hud.dismiss();
    }
}
