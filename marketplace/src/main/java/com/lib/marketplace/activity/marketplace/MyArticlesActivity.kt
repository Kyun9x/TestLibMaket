package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.lib.marketplace.adapter.marketplace.MyArticlesAdapter
import com.ipos.iposmanage.model.marrketplace.ArticlesCountModel
import com.ipos.iposmanage.model.marrketplace.ArticlesCountModelData
import com.ipos.iposmanage.model.marrketplace.ArticlesModel
import com.ipos.saler.extension.invisible
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.Log
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_my_articles.*
import kotlinx.android.synthetic.main.include_header2.*


class MyArticlesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_articles)
        initMenu()
        initData()
        initClick()
        initTab()
        api()
    }

    private fun api() {
        var articlesModel = ArticlesModel()
        articlesModel.brand_id = ApplicationIpos.getInstance().getmGlobalAppbussiness().getmBrandId()
        articlesModel.company_id = ApplicationIpos.getInstance().getmGlobalAppbussiness().getmCompanyId()
        Log.e("data","aaaa: "+ articlesModel.toJson())
        WSRestFull(applicationContext).apiSCMArticlesCount(articlesModel,
                { response ->
                    callback(response)
                },
                { error ->
                    callback(null)
                    error.printStackTrace()
                })
    }

    private fun callback(response: ArticlesCountModelData?) {
        response?.data?.run {
            initTab(this)
        }
    }

    private fun initMenu() {
        let {
            val pagerAdapter = MyArticlesAdapter(it)
            pagerMain.adapter = pagerAdapter
            pagerMain.offscreenPageLimit = 10
            pagerMain.isUserInputEnabled = false
        }
    }

    private fun initTab(data: ArticlesCountModel? = null) {
        tab_layout.removeAllTabs()
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.cho_duyet).replace("#value", "" + data?.pending)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.dang_ban).replace("#value", "" + data?.confirmed)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.het_han).replace("#value", "" + data?.expired)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.bi_huy)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.da_ban)))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        pagerMain.currentItem = 0
                    }
                    1 -> {
                        pagerMain.currentItem = 1
                    }
                    2 -> {
                        pagerMain.currentItem = 2
                    }
                    3 -> {
                        pagerMain.currentItem = 3
                    }
                    4 -> {
                        pagerMain.currentItem = 4
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { onBackPressed() }
    }


    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.grayF8)
        mlbTitle.text = getString(R.string.my_article)
        mIconRight.invisible()
        mIconSearch.invisible()
    }
}