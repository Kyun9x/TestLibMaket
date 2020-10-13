package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import com.android.volley.VolleyError
import com.ipos.iposmanage.model.marrketplace.AddressModel
import com.ipos.saler.extension.StringExt
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.ToastUtil
import com.soyagarden.android.common.extension.openActivity
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_select_order_address.*
import timber.log.Timber
import java.util.*

class SelectStartAddressActivity : BaseActivity() {

    private val mCitys = ArrayList<AddressModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_order_address)
        initData()
        initExtra()
        initClick()
        getLocate()
    }

    private fun initClick() {
        mBack.setOnClickListener { onBackPressed() }
        mStart.setOnClickListener { start() }
    }

    private fun address() {
        if (mCitys.size > 0) {
            showDialogCity()
        } else {
            getLocate()
        }
    }

    private fun showDialogCity() {
        var provinceList: MutableList<String> = ArrayList()
        Timber.e("mCitys: " + mCitys.size)
        mCitys.forEachIndexed { index, team ->
            provinceList.add(StringExt.isTextEmpty(team.city_name))
        }

        mSelectAddress.item = provinceList

        mSelectAddress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                var cityName = provinceList[position]
                selectCodeCity(cityName)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }


//        val popupMenu = PopupMenu(this, mSelectAddress, Gravity.RIGHT)
//
//        mCitys.forEachIndexed { index, team ->
//            popupMenu.menu.add(0, index.plus(1), index.plus(1), team.city_name)
//        }
//
//        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
//            val cityName = item.title.toString()
//            mSelectAddress.setText(cityName)
//            selectCodeCity(cityName)
//            false
//        }
//        popupMenu.show()
    }

    private fun selectCodeCity(title: String) {
        for (dmCityOd in mCitys) {
            if (title == dmCityOd.city_name) {
                mCartBussiness.getCartLocate().locateId = dmCityOd.id
                mCartBussiness.getCartLocate().locateName = dmCityOd.city_name
                break
            }
        }
    }

    private fun start() {
        if (TextUtils.isEmpty(mCartBussiness.getCartLocate().locateId)) {
            ToastUtil.makeText(this, getString(R.string.khuvuc_valid))
            return
        }

        onBackPressed()
        openActivity(MarketPlaceActivity::class.java)
    }

    private fun initExtra() {

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
            showDialogCity()
        }
    }

    private fun initData() {
        showStatusBar(isTranparent = true)
        getLocate()
    }
}