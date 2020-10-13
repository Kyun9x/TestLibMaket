package com.lib.marketplace.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lib.marketplace.app.ApplicationIpos;


public class TextViewRobotoMedium extends TextView {


    public TextViewRobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewRobotoMedium(Context context) {
        super(context);
        init();
    }

    private void init() {

        ApplicationIpos app = ApplicationIpos.getInstance();

        setTypeface(app.getFontBussiness().getRobotoRegular());
    }


}
