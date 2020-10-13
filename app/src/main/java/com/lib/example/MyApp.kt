package com.lib.example

import android.app.Application
import android.content.ContentValues.TAG
import android.os.Build
import android.text.TextUtils
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d("APP", "ON APP")
    }

    companion object {
        lateinit var instance: MyApp
    }
}