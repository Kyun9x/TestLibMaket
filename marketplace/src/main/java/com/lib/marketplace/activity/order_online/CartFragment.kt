package com.lib.marketplace.activity.order_online

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.ipos.iposmanage.extension.DialogUtil
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseFragment
import com.lib.marketplace.activity.CateActivity
import com.lib.marketplace.adapter.orderonline.CartRecyleAdapter
import com.lib.marketplace.model.orderonline.DmService
import com.lib.marketplace.model.orderonline.DmVoucher
import com.lib.marketplace.paser.orderonline.RestAllDmCheckAutoPromotion
import com.lib.marketplace.paser.orderonline.RestAllDmCheckVoucher
import com.lib.marketplace.paser.orderonline.RestAllDmCheckVoucherToServer
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.DialogYesNo
import com.lib.marketplace.util.FormatNumberUtil
import com.lib.marketplace.util.Log
import com.lib.marketplace.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.include_header2.*
import java.util.*

class CartFragment : BaseFragment() {
    private var mAdapter: CartRecyleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initClick()
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { v: View? -> mActivity.onBackPressed() }
        payment.setOnClickListener { v1: View? -> CateActivity.gotoInformationFragment(mActivity) }
        input_voucher.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().length > 0) {
                    check_voucher.isEnabled = true
                    check_voucher.alpha = 1f
                } else {
                    check_voucher.isEnabled = false
                    check_voucher.alpha = 0.5f
                }
            }
        })
        check_voucher.setOnClickListener { v1: View? ->
            if (mCartBussiness.getOrder().haveAutoPromotion) {
                showDialogCheckPromotion()
            } else {
                addVoucher()
            }
        }
    }

    private fun showDialogCheckPromotion() {
        val dialogYesNo: DialogYesNo = object : DialogYesNo(mActivity) {
            override val header: String?
                get() = getString(R.string.thongbao)
            override val message: String?
                get() = getString(R.string.mess_apply_voucher)

            override fun setActionYes() {
                addVoucher()
                dismiss()
            }

            override fun setActionNo() {
                dismiss()
            }
        }
        dialogYesNo.show()
    }

    private fun addVoucher() {
        val voucher = input_voucher.text.toString()
        if (!TextUtils.isEmpty(voucher)) {
            checkVoucherCode(voucher)
        }
    }

    private fun checkVoucherCode(voucherCode: String) {
        loadData()
        showProgressHub(mActivity)
        val restAllDmCheckVoucher = RestAllDmCheckVoucherToServer()
        restAllDmCheckVoucher.objects = mCartBussiness.getOrder().details
        restAllDmCheckVoucher.voucherCode = voucherCode
        WSRestFull(context).apiCheckVoucher(restAllDmCheckVoucher.toJson(), { response: RestAllDmCheckVoucher ->
            dismissProgressHub()
            onReponseCheckVoucher(response.data)
        }) { error: VolleyError ->
            dismissProgressHub()
            onReponseCheckVoucher(null)
            error.printStackTrace()
            ToastUtil.makeText(mActivity, getString(R.string.error_network2))
        }
    }

    private fun onReponseCheckVoucher(dmVoucher: DmVoucher?) {
        if (dmVoucher != null) {
            voucher_name.visibility = View.VISIBLE
            mCartBussiness.getOrder().dmVoucher = dmVoucher
            voucher_name.text = mCartBussiness.getOrder().dmVoucher.promoCampaignName
            if (DmVoucher.TYPE_DISCOUNT == dmVoucher.type) {
                mCartBussiness.getOrder().discountAmount = dmVoucher.usedDiscountAmount
                voucher_amount.text = "-" + FormatNumberUtil.formatCurrency(mCartBussiness.getOrder().discountAmount)
            } else {
                val gitfs = dmVoucher.gifts
                if (gitfs != null) {
                    for (dmService in gitfs) {
                        dmService.position = mCartBussiness.getOrder().details.size + 1
                        mCartBussiness.getOrder().details.add(dmService)
                    }
                    mAdapter?.notifyDataSetChanged()
                }
            }
            validAmountCart()
        }
    }

    private fun checkAutoPromotion() {
        loadData()
        showProgressHub(mActivity)
        val restAllDmCheckAutoPromotion = RestAllDmCheckAutoPromotion()
        restAllDmCheckAutoPromotion.datas = mCartBussiness.getOrder().details
        Log.e("rest All", "auto Promotion: " + restAllDmCheckAutoPromotion.toJson())
        WSRestFull(context).apiCheckAutoPromotion(restAllDmCheckAutoPromotion.toJson(), { response: RestAllDmCheckAutoPromotion ->
            dismissProgressHub()
            onResponePromotion(response.datas)
        }) { error: VolleyError ->
            dismissProgressHub()
            onResponePromotion(null)
            error.printStackTrace()
            ToastUtil.makeText(mActivity, getString(R.string.error_network2))
        }
    }

    private fun onResponePromotion(reponse: ArrayList<DmService>?) {
        if (reponse != null) {
            mCartBussiness.OrderOnlineAutoPromotionToDetail(reponse)
            validAmountCart()
        }
        initAdapter()
    }

    private fun initData() {
        mlbTitle.text = resources.getString(R.string.cart)
        checkAutoPromotion()
    }

    private fun initAdapter() {
        listRecyle?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            mAdapter = CartRecyleAdapter(mActivity, mCartBussiness.getOrder().cart) {
                DialogUtil.showAlertEdit(context = context, dmService = it, okListener = {
                    mCartBussiness.OrderOnlineReCheckDataOrigin(it)
                    loadData()
                    if (mCartBussiness.getOrder().haveAutoPromotion) {
                        checkAutoPromotion()
                    } else {
                        addVoucher()
                    }
                    initAdapter()
                })
            }
            adapter = mAdapter
        }
    }

    private fun loadData() {
        mCartBussiness.OrderOnlineConvertOriginToDetail()
        validAmountCart()
    }

    private fun validAmountCart() {
        provisional.text = FormatNumberUtil.formatCurrency(mCartBussiness.OrderOnlineAmountItem())
        total_amount.text = FormatNumberUtil.formatCurrency(mCartBussiness.OrderOnlineTotalAmount())
    }

    companion object {
        @JvmStatic
        fun newInstance(): CartFragment {
            return CartFragment()
        }
    }
}