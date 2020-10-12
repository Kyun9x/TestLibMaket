package com.lib.utill.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.lib.utill.R

fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().add(containerId, fragment, fragment.tag).commit()
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(containerId, fragment, fragment.tag).commit()
}

fun AppCompatActivity.openActivity(
    clz: Class<*>, bundle: Bundle? = null, clearTop: Boolean = false,
    enterAnim: Int? = null, exitAnim: Int? = null, flags: IntArray? = null
) {
    val intent = Intent(ctx, clz)
    if (flags?.isNotEmpty() == true) {
        for (flag in flags) {
            intent.addFlags(flag)
        }
    }
    if (clearTop) {
        setResult(Activity.RESULT_CANCELED)
        finishAffinity()
    }
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
    enterAnim?.also { enter ->
        exitAnim?.also { exit ->
            overridePendingTransition(enter, exit)
        }
    }
}

fun AppCompatActivity.openActivityForResult(
    clz: Class<*>,
    requestCode: Int,
    bundle: Bundle? = null,
    enterAnim: Int? = null,
    exitAnim: Int? = null
) {
    val intent = Intent(ctx, clz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
    enterAnim?.also { enter ->
        exitAnim?.also { exit ->
            overridePendingTransition(enter, exit)
        }
    }
}

fun AppCompatActivity.closeActivity(enterAnim: Int? = null, exitAnim: Int? = null) {
    finishAfterTransition()
    enterAnim?.also { enter ->
        exitAnim?.also { exit ->
            overridePendingTransition(enter, exit)
        }
    }
}

fun AppCompatActivity.hideStatusBar() {
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//    window.setFlags(
//        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//    )
}

fun AppCompatActivity.showStatusBar(
    color: Int? = null,
    isTranparent: Boolean? = null,
    statusColor: Boolean? = null
) {
    val win: Window = window
//    win.navigationBarColor = ContextCompat.getColor(this, R.color.white)
    if (isTranparent == true) {
        win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        win.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    } else {
        win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        win.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
    }
    if (statusColor == true) {
        //black
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        //white
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
    color?.run {
        win.statusBarColor = resources.getColor(this)
    } ?: run {
//        win.statusBarColor = resources.getColor(R.color.black)
    }
}

fun setMargins(
    view: View,
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
) {
    if (view.layoutParams is MarginLayoutParams) {
        val p = view.layoutParams as MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        view.requestLayout()
    }
}

fun AppCompatActivity.showToast(message: String? = null) {
    message?.run {
        Toast.makeText(this@showToast, this, Toast.LENGTH_SHORT).show()
//        Snackbar.make(window.decorView.rootView, this, Snackbar.LENGTH_SHORT).apply {
////            val snackBarView: View = view
////            snackBarView.translationY = -convertDpToPixel(80f)
//            show()
//        }
    }
}

fun AppCompatActivity.hideAllKeyboard() {
    try {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

//fun AppCompatActivity.networkIsConnected(): Boolean {
//    try {
//        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (!conMgr.activeNetworkInfo.isConnected) {
//            showToast(getString(R.string.check_connect))
//            return false
//        }
//    } catch (e: Exception) {
//        showToast(getString(R.string.check_connect))
//        e.printStackTrace()
//        return false
//    }
//    return true
//}