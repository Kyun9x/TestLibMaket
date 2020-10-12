package com.lib.utill.extension
//
//import android.content.Context
//import android.view.MotionEvent
//import android.view.View
//import android.view.View.*
//import android.view.ViewGroup
//import android.view.animation.AnimationUtils
//import android.view.animation.LayoutAnimationController
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import androidx.core.widget.NestedScrollView
//import com.google.android.material.snackbar.Snackbar
//
//val View.ctx: Context
//    get() = context
//
//var TextView.textColor: Int
//    get() = currentTextColor
//    set(value) = setTextColor(ContextCompat.getColor(com.lib.utill.extension.extension.ctx, value))
//
//
//fun View.slideExit() {
//    if (translationY == 0f) animate().translationY(-height.toFloat())
//}
//
//fun View.slideEnter() {
//    if (translationY < 0f) animate().translationY(0f)
//}
//
//fun View.gone() {
//    visibility = GONE
//}
//
//fun View.visible() {
//    visibility = VISIBLE
//}
//
//fun View.invisible() {
//    visibility = INVISIBLE
//}
//
//fun ViewGroup.setAnimation(visibility: Int, animation: Int) {
//    val anim = AnimationUtils.loadAnimation(com.lib.utill.extension.extension.ctx, animation)
//    val animController = LayoutAnimationController(anim)
//
//    this.visibility = visibility
//    layoutAnimation = animController
//    startAnimation(anim)
//}
//
//fun View.showSnackbar(
//    msg: Any?,
//    length: Int = Snackbar.LENGTH_SHORT,
//    action: Any? = null,
//    listener: (() -> Unit)? = null
//) {
//    val strMsg = when (msg) {
//        is CharSequence -> msg
//        is String -> msg
//        is Int -> com.lib.utill.extension.extension.ctx.getString(msg)
//        else -> "Message format is not supported"
//    }
//    val snackBar = Snackbar.make(this, strMsg, length)
//    if (action != null) {
//        val strAction = when (action) {
//            is CharSequence -> action
//            is String -> action
//            is Int -> com.lib.utill.extension.extension.ctx.getString(action)
//            else -> ""
//        }
//
//        if (strAction.trim().isNotEmpty()) {
//            snackBar.setAction(strAction) {
//                listener?.invoke()
//            }
//        }
//    }
//    snackBar.show()
//}
//
//fun NestedScrollView.scrollToBottom() {
//    val lastChild = getChildAt(childCount - 1)
//    val bottom = lastChild.bottom + paddingBottom
//    val delta = bottom - (scrollY + height)
//    smoothScrollBy(0, delta)
//}
//
//class DebouncingOnClickListener(
//    private val intervalMillis: Long,
//    private val doClick: ((View) -> Unit)
//) : View.OnClickListener {
//
//    override fun onClick(v: View) {
//        if (enabled) {
//            enabled = false
//            v.postDelayed(ENABLE_AGAIN, intervalMillis)
//            doClick(v)
//        }
//    }
//
//    companion object {
//        @JvmStatic
//        var enabled = true
//        private val ENABLE_AGAIN = Runnable { enabled = true }
//    }
//}
//
//class DrawbleOnClickListener(
//    private val intervalMillis: Long,
//    private val doClick: ((View) -> Unit)
//) : View.OnTouchListener {
//
//    companion object {
//        @JvmStatic
//        var enabled = true
//        private val ENABLE_AGAIN_DRAWBLE = Runnable { enabled = true }
//    }
//
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        val DRAWABLE_RIGHT = 2
//        if (event?.action == MotionEvent.ACTION_UP) {
//            if (event.rawX >= v?.right?.minus(50)!!) {
//                if (enabled) {
//                    enabled = false
//                    v.postDelayed(ENABLE_AGAIN_DRAWBLE, intervalMillis)
//                    doClick(v)
//                }
//                return true
//            }
//        }
//        return false
//    }
//}
//
//fun View.setOnCLick(intervalMillis: Long = 0, doClick: (View) -> Unit) =
//    setOnClickListener(
//        DebouncingOnClickListener(
//            intervalMillis = intervalMillis,
//            doClick = doClick
//        )
//    )
//
//fun View.setOnDrawClick(intervalMillis: Long = 0, doClick: (View) -> Unit) =
//    setOnTouchListener(
//        DrawbleOnClickListener(
//            intervalMillis = intervalMillis,
//            doClick = doClick
//        )
//    )
