package com.lib.marketplace.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lib.marketplace.app.ApplicationIpos;


public class TextViewOpenSanRegular extends androidx.appcompat.widget.AppCompatTextView {


    public TextViewOpenSanRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewOpenSanRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewOpenSanRegular(Context context) {
        super(context);
        init();
    }

    private void init() {

        ApplicationIpos app = ApplicationIpos.instance;

        setTypeface(app.getFontBussiness().getOpenSanRegular());

    }


}
