package com.example.minhhailib

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun AppCompatActivity.showToast(message: String? = null) {
    message?.run {
        Toast.makeText(this@showToast, this, Toast.LENGTH_SHORT).show()
    }
}