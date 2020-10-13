package com.lib.marketplace.activity.order_online

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.ipos.iposmanage.extension.DialogUtil
import com.lib.marketplace.model.orderonline.DmServiceListOrigin
import com.ipos.saler.extension.openActivityForResult
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseFragment
import com.lib.marketplace.activity.CateActivity
import com.lib.marketplace.adapter.orderonline.ServiceListRecyleAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.holder.orderonline.ServicelistRecycleHolder
import com.lib.marketplace.paser.orderonline.RestAllDmServiceListOrigin
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.Log
import com.lib.marketplace.util.ToastUtil
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import kotlinx.android.synthetic.main.fragment_purchase.*
import java.util.*

class PurchaseFragment : BaseFragment() {

    private var mAdapter: ServiceListRecyleAdapter? = null
    private val mDatas = ArrayList<DmServiceListOrigin>()
    private val RC_DETAL_CALLBACK = 261

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
                            PurchaseDetailActivity::class.java,
                            RC_DETAL_CALLBACK,
                            bundle = bundle
                    )
                }

                override fun onPlus(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        var quantity = dmServiceListOrigin.quantity
                        quantity++
                        if (DmServiceListOrigin.TYPE_SUB == dmServiceListOrigin.type) {
                            if (quantity >= dmServiceListOrigin.maxChoice) {
                                quantity = dmServiceListOrigin.maxChoice
                            }
                        }
                        dmServiceListOrigin.quantity = quantity
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onMinus(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        var quantity = dmServiceListOrigin.quantity
                        quantity--
                        if (DmServiceListOrigin.TYPE_SUB == dmServiceListOrigin.type) {
                            if (quantity <= dmServiceListOrigin.maxChoice) {
                                quantity = 0.0
                            }
                        }
                        dmServiceListOrigin.quantity = quantity
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onAdd(dmServiceListOrigin: DmServiceListOrigin) {
                    checkOrderType {
                        if (DmServiceListOrigin.TYPE_SUB == dmServiceListOrigin.type) {
                            dmServiceListOrigin.quantity = dmServiceListOrigin.minChoice
                        } else {
                            dmServiceListOrigin.quantity = 1.0
                        }
                        listDetails()
                        mAdapter?.notifyDataSetChanged()
                    }
                }
            })
            val headersDecor = StickyRecyclerHeadersDecoration(mAdapter)
            mRecyclerView.addItemDecoration(headersDecor)
            mRecyclerView.adapter = mAdapter
        }
    }

    private fun checkOrderType(confirm: (() -> Unit)? = null) {
        if (mCartBussiness.getOrder().orderType == Constants.OrderType.OrderNvl) {
            context?.let {
                DialogUtil.showAlert(it, textTitle = R.string.thongbao, textMessage = R.string.valid_cart_order_nvl, textCancel = R.string.btn_cancel, okListener = {
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

    private fun callServiceList() {
        showProgressHub(mActivity)
        WSRestFull(context).apiOrderOnline_ServiceList(Constants.POSPC, { response: RestAllDmServiceListOrigin -> onResponseServiceList(response.data) }) { error: VolleyError ->
            onResponseServiceList(null)
            error.printStackTrace()
            ToastUtil.makeText(mActivity, getString(R.string.error_network2))
        }
    }

    private fun onResponseServiceList(response: ArrayList<DmServiceListOrigin>?) {
        dismissProgressHub()
        mDatas.clear()
        if (response != null) {
            mDatas.addAll(response)
            mAdapter?.notifyDataSetChanged()
        }
    }

    private fun listDetails() {
        mCartBussiness.OrderOnlineConvertItemToOrigin(mDatas)
        mQuantity.text = mCartBussiness.OrderOnlineQuantity()
        if (mCartBussiness.getOrder().originDetails.size > 0) {
            relativeLayout_cart.visibility = View.VISIBLE
            mCartBussiness.setOrderOnline(Constants.OrderType.OrderOnline)
        } else {
            relativeLayout_cart.visibility = View.GONE
            mCartBussiness.setOrderOnline("")
        }
    }

    override fun onResume() {
        super.onResume()
        if (mCartBussiness.getOrder().orderType == Constants.OrderType.OrderNvl) {
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
                            if (it.code == code) {
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

    companion object {
        @JvmStatic
        fun newInstance(): PurchaseFragment {
            return PurchaseFragment()
        }
    }
}