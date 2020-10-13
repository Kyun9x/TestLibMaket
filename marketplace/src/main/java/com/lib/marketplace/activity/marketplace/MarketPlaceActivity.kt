package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.lib.marketplace.adapter.marketplace.MarketPlaceAdapter
import com.ipos.iposmanage.extension.DialogUtil
import com.ipos.iposmanage.model.marrketplace.AddressModel
import com.ipos.saler.extension.invisible
import com.ipos.saler.extension.visible
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.app.Constants
import com.lib.marketplace.model.orderonline.DmOrderOnline
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.ToastUtil
import com.lib.marketplace.util.Utilities
import com.soyagarden.android.common.extension.openActivity
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_marketplace.*
import kotlinx.android.synthetic.main.include_header2.*
import java.util.*


class MarketPlaceActivity : BaseActivity() {

    private val mCitys = ArrayList<AddressModel>()
    private var cityCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)
        initMenu()
        initData()
        initClick()
    }

    private fun initMenu() {
        let {
            val pagerAdapter = MarketPlaceAdapter(it)
            pagerMain.adapter = pagerAdapter
            pagerMain.offscreenPageLimit = 3
            pagerMain.isUserInputEnabled = false
        }

        tab_layout.removeAllTabs()
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.phancung_phanmem)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.nguyenvatlieu)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tinraovat)))
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
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { onBackPressed() }
        mAddress.setOnClickListener { address() }
        mIconSearch.setOnClickListener { search() }
        mIconRight.setOnClickListener { more() }
    }

    private fun more() {
        val popup = PopupMenu(this, mIconRight)
        popup.menuInflater.inflate(R.menu.marketplace_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.navi_order -> {
                    openActivity(MarketHistoryActivity::class.java)
                }
                R.id.navi_article -> {
                    openActivity(MyArticlesActivity::class.java)
                }
                R.id.navi_feedback -> {
                    openActivity(FeedbackActivity::class.java)
                }
            }
            true
        }

        popup.show()
    }

    private fun search() {

    }

    private fun address() {
        if (mCitys.size > 0) {
            showDialogCity()
        } else {
            getLocate()
        }
    }

    private fun showDialogCity() {
        val popupMenu = PopupMenu(this, mAddress, Gravity.RIGHT)

        mCitys.forEachIndexed { index, team ->
            popupMenu.menu.add(0, index.plus(1), index.plus(1), team.city_name)
        }

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            if (mCartBussiness.getOrder().orderType == Constants.OrderType.OrderNvl) {
                let {
                    DialogUtil.showAlert(it, textTitle = R.string.thongbao, textMessage = R.string.valid_cart_order_nvl, textCancel = R.string.btn_cancel, okListener = {
                        mCartBussiness.OrderOnlineCartClear()
                        callbackCity(item)
                    })
                }
            }else{
                callbackCity(item)
            }

            false
        }
        popupMenu.show()
    }

    private fun callbackCity(item: MenuItem){
        val cityName = item.title.toString()
        mAddress.text = cityName
        selectCodeCity(cityName)
        Utilities.sendBoard(applicationContext, Constants.BROADCAST.BROAD_NVL, Constants.BROADCAST.REFRESH)
        Utilities.sendBoard(applicationContext, Constants.BROADCAST.BROAD_ARTICLES, Constants.BROADCAST.REFRESH)
    }

    private fun selectCodeCity(title: String) {
        for (dmCityOd in mCitys) {
            if (title == dmCityOd.city_name) {
                mCartBussiness.getCartLocate().locateId = dmCityOd.id
                mCartBussiness.getCartLocate().locateName = dmCityOd.city_name
            }
        }
    }

    private fun getLocate() {
        showProgressHub(this)
        WSRestFull(this).apiSCMCity({ (data) -> areaDone(data) }) { error: VolleyError ->
            areaDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun areaDone(data: ArrayList<AddressModel>?) {
        dismissProgressHub()
        mCitys.clear()
        if (data != null) {
            mCitys.addAll(data)
        }
    }

    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.grayF8)
        mlbTitle.text = "Marketplace"
        mIconRight.visible()
        mIconSearch.invisible()

        mCartBussiness.setOder(DmOrderOnline())
        mCartBussiness.getCartLocate().run {
            mAddress.text = locateName
        }
        getLocate()
    }
}