package com.lib.marketplace.customview;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lib.marketplace.R;

public class ControlLoadMore {

    View root;

    TextView tvTitle;

    ProgressBar loading;

    public ControlLoadMore(View r) {
        this.root = r;
        loading = root.findViewById(R.id.loading);
        tvTitle = root.findViewById(R.id.error);

    }

    public void setOnClick(OnClickListener onclick) {
        root.setOnClickListener(onclick);
    }

    public void setBackGroundWhite() {
        root.setBackgroundColor(Color.WHITE);
    }

    // true thi showloading
    // false show load fail
    public void showLoading(boolean show) {
        root.setVisibility(View.VISIBLE);
        if (show) {
            loading.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
        } else {
            loading.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(R.string.error_network);
        }
    }

    public View getView() {
        return root;
    }

    public void loadingNoresult() {

        showLoading(false);
        tvTitle.setText(R.string.load_more_no_result);
        tvTitle.setVisibility(View.INVISIBLE);

    }


    public void visiableLoadMore() {
        root.setVisibility(View.INVISIBLE);
    }

    // public void goneLoadMore() {
    //
    // tvTitle.setVisibility(View.GONE);
    // loading.setVisibility(View.GONE);
    // root.setPadding(0,0,0,0);
    // root.setVisibility(View.GONE);
    //
    // }
    public void setHiden(boolean isHide) {

        if (isHide)
            root.setVisibility(View.GONE);
        else
            root.setVisibility(View.VISIBLE);
    }

}
