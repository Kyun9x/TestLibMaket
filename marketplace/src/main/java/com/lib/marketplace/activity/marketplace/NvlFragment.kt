package com.lib.marketplace.activity.marketplace

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.ipos.iposmanage.extension.DialogUtil
import com.lib.marketplace.model.marketplace.NvlModel
import com.ipos.iposmanage.model.orderonline.DmServiceListOrigin
import com.ipos.saler.extension.openActivityForResult
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseFragment
import com.lib.marketplace.activity.CateActivity
import com.lib.marketplace.adapter.orderonline.ServiceListRecyleAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.holder.orderonline.ServicelistRecycleHolder
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.Log
import com.lib.marketplace.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_nvl.*
import java.util.*

class NvlFragment : BaseFragment() {
    private var mAdapter: ServiceListRecyleAdapter? = null
    private val mDatas = ArrayList<DmServiceListOrigin>()
    private val RC_DETAL_CALLBACK = 261

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nvl, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                    .registerReceiver(onNotice, IntentFilter(Constants.BROADCAST.BROAD_NVL))
        }
        initAdapter()
        initData()
        initClick()
    }

    private fun initClick() {
        relativeLayout_cart.setOnClickListener { view: View? ->
            CateActivity.gotoCartFragment(mActivity)
        }
    }

    private fun initData() {
        callServiceList()
    }

    private fun initAdapter() {
        mRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            mAdapter = ServiceListRecyleAdapter(mActivity, mDatas, object : ServicelistRecycleHolder.OnItemClickRycle {
                override fun onClickDes(dmServiceListOrigin: DmServiceListOrigin) {
                    val bundle = Bundle()
                    bundle.putSerializable(Constants.OBJECT, dmServiceListOrigin)
                    openActivityForResult(
                            NvlDetailActivity::class.java,
                            RC_DETAL_CALLBACK,
                            bundle = bundle
                    )
                }

                override fun onPlus(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        var quantity = dmServiceListOrigin.quantity
                        quantity++
                        dmServiceListOrigin.quantity = quantity
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onMinus(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        var quantity = dmServiceListOrigin.quantity
                        quantity--
                        dmServiceListOrigin.quantity = quantity
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onAdd(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        dmServiceListOrigin.quantity = 1.0
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }
            })
            mRecyclerView.adapter = mAdapter
        }
    }

    private fun callServiceList() {
        showProgressHub(mActivity)
        WSRestFull(context).apiSCMProducts(mCartBussiness.getCartLocate().locateId,{ (data) -> onResponseServiceList(data) }) { error: VolleyError ->
            onResponseServiceList(null)
            error.printStackTrace()
            ToastUtil.makeText(mActivity, getString(R.string.error_network2))
        }
    }

    private fun onResponseServiceList(response: ArrayList<NvlModel>?) {
        dismissProgressHub()
        mDatas.clear()
        response?.forEach {
            val dmServiceListOrigin = DmServiceListOrigin()
            dmServiceListOrigin.quantity = 0.0
            it.image_urls?.let{imageUrl->
                if(imageUrl.size > 0){
                    imageUrl[0].url_thumb?.run{
                        dmServiceListOrigin.image = this
                    }
                }

            }
            dmServiceListOrigin.imageUrls = it.image_urls
            dmServiceListOrigin.unitPrice = it.price_sale!!
            dmServiceListOrigin.name = it.name
            dmServiceListOrigin.desc = it.description
            dmServiceListOrigin.priceSale = it.price!!
            dmServiceListOrigin.productUid = it.id
            dmServiceListOrigin.unitName = it.unit?.unit_name
            dmServiceListOrigin.supplier_address = it.supplier?.supplier_address
            dmServiceListOrigin.supplier_name = it.supplier?.supplier_name
            dmServiceListOrigin.supplierUid = it.supplier_uid
            dmServiceListOrigin.code = it.id
            dmServiceListOrigin.brand_name = it.brand?.brand_name
            mDatas.add(dmServiceListOrigin)
        }
        mAdapter?.notifyDataSetChanged()
    }

    private fun checkOrderType(confirm: (() -> Unit)? = null) {
        if (mCartBussiness.getOrder().orderType == Constants.OrderType.OrderOnline) {
            context?.let {
                DialogUtil.showAlert(it, textTitle = R.string.thongbao, textMessage = R.string.valid_cart_order_online, textCancel = R.string.btn_cancel, okListener = {
                    clearData()
                    confirm?.let { it1 -> it1() }
                })
            }
        } else {
            confirm?.let { it1 -> it1() }
        }
    }

    private fun clearData() {
        mCartBussiness.OrderOnlineCartClear()
        mAdapter?.notifyDataSetChanged()
    }

    private fun listDetails() {
        mCartBussiness.OrderOnlineConvertItemToOrigin(mDatas)
        mQuantity.text = mCartBussiness.OrderOnlineQuantity()
        if (mCartBussiness.getOrder().originDetails.size > 0) {
            relativeLayout_cart.visibility = View.VISIBLE
            mCartBussiness.setOrderOnline(Constants.OrderType.OrderNvl)
        } else {
            relativeLayout_cart.visibility = View.GONE
            mCartBussiness.setOrderOnline("")
        }
    }

    override fun onResume() {
        super.onResume()
        if (mCartBussiness.getOrder().orderType == Constants.OrderType.OrderOnline) {
            mDatas.forEach {
                it.quantity = 0.0
            }
            mAdapter?.notifyDataSetChanged()
            mQuantity.text = mCartBussiness.OrderOnlineQuantity()
            relativeLayout_cart.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_DETAL_CALLBACK && resultCode == Activity.RESULT_OK) {
            data?.run {
                getSerializableExtra(Constants.OBJECT)?.let {
                    val item = it as DmServiceListOrigin
                    Log.e("datata: ", "aaa: " + Gson().toJson(item))
                    item.run {
                        mDatas.forEach {
                            if (it.productUid == productUid) {
                                checkOrderType {
                                    it.quantity = item.quantity
                                    listDetails()
                                    mAdapter?.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(onNotice)
        }
    }

    var onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            callServiceList()
        }
    }

    companion object {
        fun newInstance(): NvlFragment {
            return NvlFragment()
        }
    }
}