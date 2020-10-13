package com.lib.marketplace.activity.order_online

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ipos.iposmanage.model.orderonline.*
import com.ipos.saler.extension.StringExt
import com.ipos.saler.extension.gone
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.app.Constants
import com.lib.marketplace.model.orderonline.*
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.ToastUtil
import com.lib.marketplace.util.Utilities
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.include_header2.*
import java.util.*

class AddAddressOrderOnlineActivity : BaseActivity() {

    private val mCitys = ArrayList<DmCityOd>()
    private val mDistricts = ArrayList<DmDistrict>()
    private var cityCode = ""
    private var districtCode = ""
    private var phuongxa = ""
    private var lat = 0.0
    private var lng = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        initData()
        initClick()
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { onBackPressed() }
        mCancel.setOnClickListener { onBackPressed() }
        mAddress.setOnClickListener { address() }
        mCity.setOnClickListener { city() }
        mDistrict.setOnClickListener { district() }
        mPhuongXa.setOnClickListener { phuongxa() }
        mApply.setOnClickListener { save() }
    }

    private fun save() {
        var adressdl = mAddress.text.toString()

        if (TextUtils.isEmpty(adressdl)) {
            ToastUtil.makeText(this, getString(R.string.mess_delivery_info))
            return
        }
        if (TextUtils.isEmpty(cityCode)) {
            ToastUtil.makeText(this, getString(R.string.mess_delivery_info))
            return
        }
        if (TextUtils.isEmpty(districtCode)) {
            ToastUtil.makeText(this, getString(R.string.mess_delivery_info))
            return
        }
//        if (TextUtils.isEmpty(phuongxa)) {
//            ToastUtil.makeText(this, getString(R.string.mess_delivery_info))
//            return
//        }

        val dmDeliveryInfo = DmDeliveryInfo()
        dmDeliveryInfo.address = adressdl
        dmDeliveryInfo.city = cityCode
        dmDeliveryInfo.cityName = mCity.text.toString()
        dmDeliveryInfo.district = districtCode
        dmDeliveryInfo.districtName = mDistrict.text.toString()
        dmDeliveryInfo.lat = lat
        dmDeliveryInfo.lng = lng

        mCartBussiness.getOrder().dmDeliveryInfo = dmDeliveryInfo

        val i = Intent()
        setResult(RESULT_OK, i)
        onBackPressed()
    }

    private fun address() {
        GPS()
    }

    private fun city() {
        if (mCitys.size > 0) {
            showDialogCity()
        } else {
            getLocate()
        }
    }

    private fun district() {
        showDialogDistrict()
    }

    private fun phuongxa() {

    }

    private fun showDialogCity() {
        var popupMenu: PopupMenu? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupMenu = PopupMenu(this, mCity, Gravity.END)
        }
        for (dmCityOd in mCitys) {
            popupMenu!!.menu.add(dmCityOd.name)
        }
        popupMenu!!.setOnMenuItemClickListener { item: MenuItem ->
            val cityName = item.title.toString()
            mCity.setText(cityName)
            selectCodeCity(cityName)
            false
        }
        popupMenu.show()
    }

    private fun selectCodeCity(title: String) {
        for (dmCityOd in mCitys) {
            if (title == dmCityOd.name) {
                cityCode = dmCityOd.code
                mDistricts.clear()
                mDistricts.addAll(dmCityOd.dmDistricts)
                mDistrict.setText("")
                districtCode = ""
                break
            }
        }
    }

    private fun showDialogDistrict() {
        var popupMenu: PopupMenu? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupMenu = PopupMenu(this, mDistrict, Gravity.END)
        }
        for (dmDistrict in mDistricts) {
            popupMenu!!.menu.add(dmDistrict.name)
        }
        popupMenu!!.setOnMenuItemClickListener { item: MenuItem ->
            val cityName = item.title.toString()
            mDistrict.setText(cityName)
            selectCodeDistrict(cityName)
            false
        }
        popupMenu.show()
    }

    private fun selectCodeDistrict(title: String) {
        for (dmDistrict in mDistricts) {
            if (title == dmDistrict.name) {
                districtCode = dmDistrict.code
                break
            }
        }
    }

    private fun GPS() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                Utilities.showMessageOKCancel(this, getString(R.string.mess_acces_permission_location)
                ) { dialog: DialogInterface?, which: Int ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            } else {
                ActivityCompat.requestPermissions(this, Utilities.PERMISSIONS_LOCATION, 1)
            }
        } else {
            val i = Intent(this, MapActivity::class.java)
            startActivityForResult(i, InformationFragment.REQUEST_CODE_PICK_LOCATION)
        }
    }

    private fun getLocate() {
        showProgressHub(this)
        WSRestFull(this).apiLocate({ response ->
            dismissProgressHub()
            onresponseLocate(response.datas)
        }) { error ->
            dismissProgressHub()
            onresponseLocate(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun onresponseLocate(response: ArrayList<DmLocate>?) {
        if (response != null) {
            mCitys.clear()
            for (dmLocate in response) {
                mCitys.addAll(dmLocate.dmCities)
            }
        }
    }


    private fun initData() {
        showStatusBar(color = R.color.white, statusColor = true)
        include2.background.setTint(ContextCompat.getColor(this, R.color.white))
        mlbTitle.text = getString(R.string.edit_address)
        getLocate()
        mCartBussiness.getOrder().dmDeliveryInfo?.run{
            mAddress.setText(StringExt.isTextEmpty(address))
            mCity.setText(StringExt.isTextEmpty(cityName))
            mDistrict.setText(StringExt.isTextEmpty(districtName))
        }
        mTIPPhuong.gone()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == InformationFragment.REQUEST_CODE_PICK_LOCATION) {
            if (resultCode == RESULT_OK) {
                val dmLocation = data!!.getSerializableExtra(Constants.KEY_DATA) as DmLocation
                val ad = dmLocation.address
                lat = dmLocation.latitude
                lng = dmLocation.longitude
                mAddress.setText(ad)
            }
        }
    }
}