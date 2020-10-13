package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.ipos.saler.extension.invisible
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.adapter.marketplace.MarketHistoryAdapter
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_marketplace_history.*
import kotlinx.android.synthetic.main.include_header2.*


class MarketHistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace_history)
        initClick()
        initData()
        initMenu()
    }

    private fun initMenu() {
        let {
            val pagerAdapter = MarketHistoryAdapter(it)
            pagerMain.adapter = pagerAdapter
            pagerMain.offscreenPageLimit = 2
            pagerMain.isUserInputEnabled = false
        }

        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.phancung_phanmem)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.nguyenvatlieu)))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> pagerMain.currentItem = 0
                    1 -> pagerMain.currentItem = 1
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
        mlbTitle.text = getString(R.string.order_history)
        mIconRight.invisible()
    }
}