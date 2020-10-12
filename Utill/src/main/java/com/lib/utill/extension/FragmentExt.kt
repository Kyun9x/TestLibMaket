package com.example.utill.extension

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ipos.saler.R

val Fragment.ctx: Context?
    get() = context

val Fragment.TAG: String
    get() = this::class.java.simpleName

fun Fragment.addFragment(containerId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction().add(containerId, fragment, fragment.TAG).commit()
}

fun Fragment.replaceFragment(containerId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction().replace(containerId, fragment, fragment.TAG)
        .addToBackStack(null).commit()
}

fun Fragment.openActivity(
    clz: Class<*>,
    bundle: Bundle? = null,
    enterAnim: Int? = null,
    exitAnim: Int? = null,
    flags: IntArray? = null
) {
    val intent = Intent(ctx, clz)
    if (flags?.isNotEmpty() == true) {
        for (flag in flags) {
            intent.addFlags(flag)
        }
    }
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
    enterAnim?.also { enter ->
        exitAnim?.also { exit ->
            activity?.overridePendingTransition(enter, exit)
        }
    }
}

fun Fragment.openActivityForResult(clz: Class<*>, requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(ctx, clz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
}

fun Fragment.hideStatusBar() {
    activity?.run {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}

fun Fragment.showStatusBar(color: Int? = null, isTranparent: Boolean? = null) {
    activity?.run {
        if (isTranparent == true) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        color?.run {
            window.statusBarColor = resources.getColor(this)
        } ?: run {
            window.statusBarColor = resources.getColor(R.color.black)
        }
    }
}


fun Fragment.openNewTabWindow(urls: String, context: Context) {
    val uris = Uri.parse(urls)
    val intents = Intent(Intent.ACTION_VIEW, uris)
    val b = Bundle()
    b.putBoolean("new_window", true)
    intents.putExtras(b)
    context.startActivity(intents)
}
