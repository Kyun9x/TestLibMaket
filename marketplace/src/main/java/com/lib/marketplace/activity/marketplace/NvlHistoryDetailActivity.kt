package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.activity.order_online.OrderDetailFragment
import com.lib.marketplace.adapter.orderonline.OrderDetailRecyleAdapter
import com.lib.marketplace.adapter.orderonline.StatusOrderRecyleAdapter
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.app.Constants
import com.lib.marketplace.model.marketplace.NvlOnlineModel
import com.lib.marketplace.model.orderonline.DmService
import com.lib.marketplace.model.orderonline.DmStatusOrder
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.FormatNumberUtil
import com.lib.marketplace.util.ToastUtil
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.fragment_orderonline_detail.*
import kotlinx.android.synthetic.main.include_header_order_detail.*
import java.util.*

class NvlHistoryDetailActivity : BaseActivity() {

    val TYPE_ORDER_HISTORY = "TYPE_ORDER_HISTORY"
    val TYPE_CREATE_ORDER = "TYPE_CREATE_ORDER"

    private val mListStatus = java.util.ArrayList<DmStatusOrder>()
    private var mAdapterStatus: StatusOrderRecyleAdapter? = null
    private var details = ArrayList<DmService>()
    private var mAdapter: OrderDetailRecyleAdapter? = null
    private var type = ""
    private var uId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_orderonline_detail)
        initExtra()
        initAdapter()
        initData()
        initClick()
    }

    private fun initExtra() {
        type = intent?.extras?.let {
            if (it.containsKey(Constants.DATA)) {
                it.getSerializable(Constants.DATA) as String?
            } else {
                null
            }
        }.toString()

        uId = intent?.extras?.let {
            if (it.containsKey(Constants.OBJECT)) {
                it.getSerializable(Constants.OBJECT) as String?
            } else {
                null
            }
        }.toString()

        uId.run{
            api(this)
        }
    }

    private fun api(uId: String) {
        showProgressHub(this)
        WSRestFull(this).apiSCMInvoicesHistoryDetail(mCartBussiness.companyId, uId, { (data) -> apiDone(data) }) { error: VolleyError ->
            apiDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun apiDone(data: NvlOnlineModel?) {
        dismissProgressHub()
        data?.run{
            loadDataStatus(this)
            header_text.text = resources.getString(R.string.don_hang) + " #" + invoice_id
            mPaymentmethod.text = getString(R.string.cod_e_wallet)
            mPaymentmethod.isEnabled = false
            mPaymentmethod.setTextColor(ContextCompat.getColor(this@NvlHistoryDetailActivity, R.color.gray80))
            mPaymentmethod.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_payment_unselect, 0)

            var position = 0
            for (i in mListStatus.indices) {
                var textStatus = ""
                if(status == Constants.OrderNvlStatus.CANCELED){
                    textStatus = getString(R.string.da_huy)
                }else if (status == Constants.OrderNvlStatus.COMPLETED) {
                    textStatus = getString(R.string.hoan_thanh)
                }else if (status == Constants.OrderNvlStatus.PENDING) {
                    textStatus = getString(R.string.cho_xy_ly)
                }else if (status == Constants.OrderNvlStatus.CONFIRMED) {
                    textStatus = getString(R.string.da_xac_nhan)
                }

                if (textStatus == mListStatus[i].type) {
                    position = i
                    mListStatus[i].isCorectPosition = true
                } else {
                    mListStatus[i].isCorectPosition = false
                }
            }

            for (i in 0..position) {
                mListStatus[i].isCheckedStatus = true
            }
            mAdapterStatus?.notifyDataSetChanged()

            provisional.text = voucherAmount?.let { original_amount?.plus(it) }?.let { FormatNumberUtil.formatCurrency(it) }
            voucher_amount.text = "-" + voucherAmount?.let { FormatNumberUtil.formatCurrency(it) }
            total_amount.text = final_amount?.let { FormatNumberUtil.formatCurrency(it) }

            details.clear()
            invoice_details.forEach {
                it.product?.run{
                    var dmService = DmService()
                    dmService.serviceName = name
                    price?.run{
                        dmService.orgPrice = this
                    }
                    it.quantity?.run{
                        dmService.quantity = this.toDouble()
                    }
                    dmService.amount = dmService.orgPrice * dmService.quantity
                    details.add(dmService)
                }
            }
            mAdapter?.notifyDataSetChanged()

        }
    }

    private fun initAdapter() {
        list_status?.apply {
            layoutManager = LinearLayoutManager(this@NvlHistoryDetailActivity, RecyclerView.VERTICAL, false)
            mAdapterStatus = StatusOrderRecyleAdapter(this@NvlHistoryDetailActivity, mListStatus)
            adapter = mAdapterStatus
        }
        list_detail?.apply {
            layoutManager = LinearLayoutManager(this@NvlHistoryDetailActivity, RecyclerView.VERTICAL, false)
            mAdapter = OrderDetailRecyleAdapter(this@NvlHistoryDetailActivity, details)
            adapter = mAdapter
        }
    }

    private fun loadDataStatus(data: NvlOnlineModel?) {
        mListStatus.clear()
        mListStatus.add(DmStatusOrder(Constants.OrderNvlStatus.PENDING, getString(R.string.cho_xy_ly), true, true, false, true))
        mListStatus.add(DmStatusOrder(Constants.OrderNvlStatus.CONFIRMED, getString(R.string.da_xac_nhan), false, false, false, false))
        if(data?.status == Constants.OrderNvlStatus.CANCELED){
            mListStatus.add(DmStatusOrder(Constants.OrderNvlStatus.CANCELED, getString(R.string.da_huy), false, false, true, false))
        }else{
            mListStatus.add(DmStatusOrder(Constants.OrderNvlStatus.COMPLETED, getString(R.string.hoan_thanh), false, false, true, false))
        }
    }

    private fun initClick() {
        back.setOnClickListener {onBack()}
    }

    private fun onBack(){
        if (OrderDetailFragment.TYPE_CREATE_ORDER == type) {
//            val intent = Intent(this,
//                    HomeAcvity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
            finish()
        } else {
            onBackPressed()
        }
    }

    private fun initData() {
        showStatusBar(color = R.color.grayF8, statusColor = true)
    }
}