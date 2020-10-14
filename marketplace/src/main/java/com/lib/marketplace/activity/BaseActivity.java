package com.lib.marketplace.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lib.marketplace.R;
import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.bussiness.CartBussiness;
import com.lib.marketplace.bussiness.FontBussiness;
import com.lib.marketplace.util.Log;
import com.lib.marketplace.util.SharedPref;

import ly.count.android.sdk.Countly;


public class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    protected SharedPref config;
    protected Handler mHandler;


    protected TextView mTitle;
    protected ImageView mIconMenu;
    protected ImageView mIconMore;


    protected boolean isKeyBoard = false;
    protected int keyboardHeight;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    protected boolean isKeyBoardVisible;
    protected CartBussiness mCartBussiness;
    protected KProgressHUD hud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();
        config = new SharedPref(this);
        mHandler = new Handler();

        mCartBussiness = ApplicationIpos.instance.getCartBussiness();
    }

    protected void finviewHomeBar() {
    }

    protected void initDataHomebar() {
        mIconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    /**
     * execute replacing fragment transaction
     *
     * @param fragment         Fragment to be display
     * @param isAddToBackStack true if could navigate
     */
    protected void executeFragmentTransaction(Fragment fragment, int fragmentParentId,
                                              boolean isAddToBackStack, boolean isAnimations) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
//        if (isAnimations) {
//            mFragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right,
//                    R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right);
//        }
        // Add the fragment to the 'fragment_container' FrameLayout
        mFragmentTransaction.replace(fragmentParentId, fragment);
        //  mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        if (isAddToBackStack) {
//            mFragmentTransaction.addToBackStack(null);
//        }
        mFragmentTransaction.commit();
    }


    public void shareAction(Bundle item) {
        // Log.i("BaseActivity.shareAction()", "URL " + item.getUrl());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,
                item.getString(Constants.KEY_SUBJECT));
        intent.putExtra(Intent.EXTRA_TEXT,
                item.getString(Constants.KEY_URL));
        intent.putExtra(Intent.EXTRA_TITLE,
                item.getString(Constants.KEY_TITLE));
        startActivity(intent);

    }

    public void actionShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        startActivity(intent);
    }

    public void actionSendReport() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{Constants.MAIL_FB});
//			intent.putExtra(android.content.Intent.EXTRA_TEXT,
//					getString(R.string.send_feedback_content) + " "
//							+ getString(R.string.app_name));

        startActivity(Intent.createChooser(intent, "Send Mail"));

    }


    // ===init keyboard show====//

    /**
     * change height of emoticons keyboard according to height of actual
     * keyboard
     *
     * @param height minimum height by which we can make sure actual keyboard is
     *               open or not
     */
    private void changeKeyboardHeight(int height) {

        if (height > 100) {
            keyboardHeight = height;
            config.putInt(Constants.HEIGHT_KEY, keyboardHeight);

        }

    }

    /*
     * * Checking keyboard height and keyboard visibility
     */
    int previousHeightDiffrence = 0;

    protected void checkKeyboardHeight(final View parentLayout) {

        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        parentLayout.getWindowVisibleDisplayFrame(r);

                        int screenHeight = parentLayout.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);

                        previousHeightDiffrence = heightDifference;
                        if (heightDifference > 100) {

                            isKeyBoardVisible = true;
                            changeKeyboardHeight(heightDifference);

                            if (!isKeyBoard) {
                                isKeyBoard = true;
                                Log.i("Keyboard", " Keyboard show");
                                showKeyboard();
                            }
                        } else {

                            if (isKeyBoard) {
                                isKeyBoard = false;
                                Log.i("KEyboard.", "Keyboard Off");
                                hidenKeyboard();

                            }

                            isKeyBoardVisible = false;

                        }

                    }
                });

    }

    protected void showKeyboard() {

    }

    protected void hidenKeyboard() {

    }


    @Override
    public void onStart() {
        super.onStart();
        Countly.sharedInstance().onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Countly.sharedInstance().onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Countly.sharedInstance().onConfigurationChanged(newConfig);
    }

}
