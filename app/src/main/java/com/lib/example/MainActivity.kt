package com.lib.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApplicationIpos.instance = ApplicationIpos()
        ApplicationIpos.instance.loadData(companyId = "aaaa")
        Log.e("aaa","bb: "+ ApplicationIpos.instance.cartBussiness?.companyId)
    }
}