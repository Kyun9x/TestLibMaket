package com.lib.marketplace.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lib.marketplace.app.ApplicationIpos;

public class TextViewOpenSanLight extends androidx.appcompat.widget.AppCompatTextView {


    public TextViewOpenSanLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewOpenSanLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewOpenSanLight(Context context) {
        super(context);
        init();
    }

    private void init() {

        ApplicationIpos app = ApplicationIpos.instance;

        setTypeface(app.getFontBussiness().getOpenSanLight());
    }


}
