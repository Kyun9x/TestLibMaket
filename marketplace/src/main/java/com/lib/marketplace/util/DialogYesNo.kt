package com.lib.marketplace.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.lib.marketplace.R
import kotlinx.android.synthetic.main.dialog_alert.*

abstract class DialogYesNo : Dialog, View.OnClickListener {

    constructor(
        context: Context?, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context!!, cancelable, cancelListener) {
        init()
    }

    constructor(context: Context?, theme: Int) : super(context!!, theme) {
        init()
    }

    constructor(context: Context?) : super(context!!, R.style.style_dialog2) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_alert)
        init()
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
    }

    fun setOneButton() {
        btn_cancel!!.visibility = View.GONE
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ) as MarginLayoutParams
        )
        params.gravity = Gravity.CENTER
        btn_ok!!.layoutParams = params
        btn_ok!!.gravity = Gravity.CENTER
        val padding = context.resources.getDimensionPixelSize(R.dimen.action_bar_height_40)
        btn_ok!!.setPadding(padding, 0, padding, 0)
    }

    private fun init() {
        if (header == null) {
        } else {
            lbl_dialog_header.setText(header)
        }
        lbl_dialog_text.setText(message)
        btn_cancel.setOnClickListener(this)
        btn_ok.setOnClickListener(this)
        window!!.attributes.windowAnimations = R.style.PopupAnimation
        setCanceledOnTouchOutside(true)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_ok -> setActionYes()
            R.id.btn_cancel -> setActionNo()
            else -> {
            }
        }
    }

    abstract val header: String?
    abstract val message: String?

    abstract fun setActionYes()
    abstract fun setActionNo()

    fun setNameButtonYes(resID: Int) {
        btn_ok!!.setText(resID)
    }

    fun setNameButtonNo(resID: Int) {
        btn_cancel!!.setText(resID)
    }
}