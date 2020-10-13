package com.lib.marketplace.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ipos.iposmanage.app.ApplicationIpos;

public class TextViewRobotoBold extends TextView {


    public TextViewRobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRobotoBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewRobotoBold(Context context) {
        super(context);
        init();
    }

    private void init() {

        ApplicationIpos app = ApplicationIpos.getInstance();

        setTypeface(app.getFontBussiness().getRobotoBold());
    }


}
