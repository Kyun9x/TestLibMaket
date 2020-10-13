package com.ipos.saler.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lib.marketplace.R
import com.lib.marketplace.app.ApplicationIpos
import de.hdodenhof.circleimageview.CircleImageView

fun initAvatar(view: CircleImageView, path: Any?, error: Drawable? = null) {
    Glide.with(ApplicationIpos.getInstance()).load(path).apply(RequestOptions().placeholder(error).override(100, 100)).into(view)
}

fun initAvatarCompany(view: ImageView, path: Any?, error: Drawable? = null) {
    Glide.with(ApplicationIpos.getInstance()).load(path).apply(
            RequestOptions().placeholder(error)
    ).into(view)
}

fun initIcon(view: ImageView, path: Any?) {
    Glide.with(ApplicationIpos.getInstance()).load(path).apply(
            RequestOptions().placeholder(R.drawable.icon_default).override(100, 100)
    ).into(view)
}

fun initIconNoDefault(view: ImageView, path: Any?) {
    Glide.with(ApplicationIpos.getInstance()).load(path).into(view)
}