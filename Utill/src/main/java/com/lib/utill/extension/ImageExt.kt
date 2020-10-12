package com.lib.utill.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ipos.saler.MyApplication
import com.ipos.saler.R
import de.hdodenhof.circleimageview.CircleImageView

fun initAvatar(view: CircleImageView, path: Any?, error: Drawable? = null) {
    Glide.with(MyApplication.instance).load(path)
        .apply(RequestOptions().placeholder(error).override(100, 100)).into(view)
}

fun initAvatarCompany(view: ImageView, path: Any?, error: Drawable? = null) {
    Glide.with(MyApplication.instance).load(path).apply(
        RequestOptions().placeholder(error).override(300, 300)
    ).into(view)
}

fun initIcon(view: ImageView, path: Any?) {
    Glide.with(MyApplication.instance).load(path).apply(
        RequestOptions().placeholder(R.drawable.icon_defaul).override(100, 100)
    ).into(view)
}

fun initIconNoDefault(view: ImageView, path: Any?) {
    Glide.with(MyApplication.instance).load(path).into(view)
}