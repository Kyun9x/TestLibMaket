package com.lib.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ApplicationIpos.instance = ApplicationIpos()
//        ApplicationIpos.instance.loadData(companyId = "aaaa")
//        Log.e("aaa","bb: "+ ApplicationIpos.instance.cartBussiness?.companyId)
    }
}