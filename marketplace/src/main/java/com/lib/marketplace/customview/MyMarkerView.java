//package com.lib.marketplace.customview;
//
//import android.content.Context;
//import android.widget.TextView;
//
//import com.github.mikephil.charting.components.MarkerView;
//import com.github.mikephil.charting.data.CandleEntry;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.highlight.Highlight;
//import com.lib.marketplace.R;
//
//
///**
// * Custom implementation of the MarkerView.
// *
// * @author Philipp Jahoda
// */
//public class MyMarkerView extends MarkerView {
//
//    private TextView tvContent;
//
//    public MyMarkerView(Context context, int layoutResource) {
//        super(context, layoutResource);
//
//        tvContent = findViewById(R.id.tvContent);
//    }
//
//    // callbacks everytime the MarkerView is redrawn, can be used to update the
//    // content (user-interface)
//    @Override
//    public void refreshContent(Entry e, Highlight highlight) {
//
//        float value;
//        if (e instanceof CandleEntry) {
//
//            CandleEntry ce = (CandleEntry) e;
//            value = ce.getHigh();
//
//        } else {
//            value = e.getVal();
//
//        }
//        tvContent.setText("" + value);
//    }
//
//    @Override
//    public int getXOffset(float xpos) {
//        return -(getWidth() / 2);
//    }
//
//    @Override
//    public int getYOffset(float ypos) {
//        return -getHeight();
//    }
//
//
//}
