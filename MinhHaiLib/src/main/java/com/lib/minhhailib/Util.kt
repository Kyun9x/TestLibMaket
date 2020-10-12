package com.lib.minhhailib

import android.content.Context
import android.widget.Toast

object LMHLib {
    fun showToast(c: Context?, message: String? = null) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}