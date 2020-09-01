package com.example.minhhailib

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


fun AppCompatActivity.showToast(message: String? = null) {
    message?.run {
        Toast.makeText(this@showToast, this, Toast.LENGTH_SHORT).show()
    }
}

fun showMessage(c: Context?, message: String?) {
    Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
}
