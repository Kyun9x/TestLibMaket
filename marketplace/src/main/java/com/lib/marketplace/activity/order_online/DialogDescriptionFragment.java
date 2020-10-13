package com.lib.marketplace.activity.order_online;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lib.marketplace.model.orderonline.DmServiceListOrigin;
import com.lib.marketplace.R;
import com.lib.marketplace.util.Utilities;

public class DialogDescriptionFragment extends DialogFragment {
    protected static final String TAG = DialogDescriptionFragment.class.getName();
    private View v;
    private DmServiceListOrigin dmServiceListOrigin;
    private SimpleDraweeView mImage;
    private TextView mTitle;
    private TextView mDesc;

    protected int getItemLayout() {
        return R.layout.dialog_description_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogSlideAnim;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans100)));
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(getItemLayout(), null);
        mImage = v.findViewById(R.id.image);
        mTitle = v.findViewById(R.id.title);
        mDesc = v.findViewById(R.id.des);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
    Glide.with(this).load(dmServiceListOrigin.getImage()).into(mImage);

        mTitle.setText(dmServiceListOrigin.getName());
        mDesc.setText(dmServiceListOrigin.getDesc());
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Utilities.hideKeyboard(v, getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


    public static DialogDescriptionFragment newInstance(DmServiceListOrigin dmServiceListOrigin) {
        DialogDescriptionFragment fragment = new DialogDescriptionFragment();
        fragment.dmServiceListOrigin = dmServiceListOrigin;
        return fragment;
    }


}
