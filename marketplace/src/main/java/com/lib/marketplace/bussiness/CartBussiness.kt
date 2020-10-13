package com.lib.marketplace.bussiness

import com.google.gson.Gson
import com.lib.marketplace.model.marketplace.NvlModel
import com.lib.marketplace.model.marketplace.NvlOnlineModel
import com.ipos.saler.extension.StringExt
import com.lib.marketplace.model.marketplace.CartLocateModel
import com.lib.marketplace.model.orderonline.*
import com.lib.marketplace.util.ConverUtil
import com.lib.marketplace.util.Log

class CartBussiness {
    private var mOrderModel = DmOrderOnline()
    private var dmCartLocate = CartLocateModel()
    var mListBrands = ArrayList<DmBrand>()

    //Adding Data
    var appType = ""
    var companyId = ""
    var brandId = ""
    var userId = ""
    var listBrand = ArrayList<DmBrand>()
    var listStore = ArrayList<DmStore>()

    fun addBrand(data: ArrayList<DmBrand>){
        mListBrands.clear()
        data?.run{
            mListBrands.addAll(this)
        }
    }
    //Order Online
    fun getOrder(): DmOrderOnline {
        return mOrderModel
    }

    fun setOder(order: DmOrderOnline) {
        mOrderModel = DmOrderOnline()
        mOrderModel = order
    }

    fun convertOrderToJson(): DmOrderOnline {
        var orderOnline = DmOrderOnline()

        orderOnline.isHaveAutoPromotion = null
        orderOnline.originDetails = null
        orderOnline.companyId = mOrderModel.companyId
        orderOnline.contactCompany = mOrderModel.contactCompany
        orderOnline.contactName = mOrderModel.contactName
        orderOnline.customerName = mOrderModel.customerName
        orderOnline.companyTaxCode = mOrderModel.companyTaxCode
        orderOnline.companyTaxEmail = mOrderModel.companyTaxEmail
        orderOnline.companyFullAddress = mOrderModel.companyFullAddress
        orderOnline.customerNote = mOrderModel.customerNote
        orderOnline.requestInvoice = mOrderModel.requestInvoice

        orderOnline.storeId = mOrderModel.storeId
        orderOnline.storeName = mOrderModel.storeName
        orderOnline.brandId = mOrderModel.brandId
        orderOnline.dmDeliveryInfo = mOrderModel.dmDeliveryInfo

        orderOnline.details.addAll(mOrderModel.details)
        Log.e("json", "data: " + Gson().toJson(orderOnline))
        return orderOnline
    }

    fun convertNvlToJson(): NvlOnlineModel {
        var nvlOnline = NvlOnlineModel()

        nvlOnline.address = mOrderModel.dmDeliveryInfo.address
        nvlOnline.original_amount = mOrderModel.amount
        mOrderModel.discountAmount?.run {
            nvlOnline.discount_amount = this
        }
        mOrderModel.dmVoucher?.voucherCode?.run {
            nvlOnline.voucher_code = this
        }
        mOrderModel.dmVoucher?.usedDiscountAmount?.run {
            nvlOnline.voucherAmount = this
        }
        nvlOnline.final_amount = mOrderModel.amount
        nvlOnline.is_red_invoice = mOrderModel.requestInvoice
        nvlOnline.customer_id = userId
        nvlOnline.customer_name = userId
        nvlOnline.recipient_name = mOrderModel.contactName
        nvlOnline.recipient_id = mOrderModel.dmDeliveryInfo?.receiverPhone
        nvlOnline.company_id = companyId

        mOrderModel.companyTaxCode?.run {
            nvlOnline.tax_code = this
        }
        mOrderModel.companyTaxEmail?.run {
            nvlOnline.legal_email = this
        }
        mOrderModel.companyFullAddress?.run {
            nvlOnline.legal_address = this
        }
        mOrderModel.customerNote?.run {
            nvlOnline.note = this
        }
        nvlOnline.brand_id = mOrderModel.brandId
        nvlOnline.store_id = mOrderModel.storeId
        nvlOnline.location_uid = mOrderModel.dmDeliveryInfo?.locationUid
        nvlOnline.supplier_uid = mOrderModel.details[0]?.supplierUid
        nvlOnline.legal_person = mOrderModel.customerName

        nvlOnline.invoice_origin = null
        mOrderModel.details.forEach {
            var nvlModel = NvlModel(quantity = it.quantity.toInt(), product_uid = it.productUid, description = null, id = null, image_thumb = null, image_urls = null, name = null, price = null, supplier_uid = null, unit = null)
            nvlOnline.invoice_details.add(nvlModel)
        }
        Log.e("json", "data: " + Gson().toJson(nvlOnline))
        return nvlOnline
    }

    fun setOrderOnline(orderType: String) {
        mOrderModel.orderType = orderType
    }

    fun OrderOnlineAmountItem(): Double {
        var amount = 0.0
        mOrderModel.details?.forEach {
            amount += it.amount
        }
        return amount
    }

    fun OrderOnlineQuantity(): String {
        var total = 0
        mOrderModel.originDetails?.forEach {
            total = total.plus(it.quantity.toInt())
        }

        return "" + total
    }

    fun OrderOnlineTotalAmount(): Double {
        var amount = OrderOnlineAmountItem()
        mOrderModel.amount = amount - mOrderModel.discountAmount
        return amount
    }

    fun OrderOnlineCartClear() {
        mOrderModel.originDetails.clear()
        mOrderModel.details.clear()
        mOrderModel.dmDeliveryInfo = DmDeliveryInfo()
        mOrderModel.orderType = ""
    }

    fun OrderOnlineConvertItemToOrigin(mDatas: ArrayList<DmServiceListOrigin>) {
        mOrderModel.originDetails.clear()
        for (item in mDatas) {
            if (item.quantity > 0) {
                var convertDetail = ArrayList<DmServiceListOrigin>()
                item.details?.forEach {
                    convertDetail.add(it.copy())
                }

                var service = item.copy()
                service.details = convertDetail
                mOrderModel.originDetails.add(service)
            }
        }
    }

    fun OrderOnlineReCheckDataOrigin(dmService: DmService) {
        val it: MutableIterator<DmServiceListOrigin> = mOrderModel.originDetails.iterator()
        while (it.hasNext()) {
            val dmServiceListOrigin: DmServiceListOrigin = it.next()
            if (dmService.serviceName == dmServiceListOrigin.name) {
                if (dmService.quantity == 0.0) {
                    mOrderModel.originDetails.remove(dmServiceListOrigin)
                } else {
                    dmServiceListOrigin.quantity = dmService.quantity
                }
            }
        }
    }

    fun OrderOnlineConvertOriginToDetail() {
        mOrderModel.details.clear()
        for (dmServiceListOrigin in mOrderModel.originDetails) {
            if (DmServiceListOrigin.TYPE_COMBO == dmServiceListOrigin.type) {
                val details = dmServiceListOrigin.details
                if (details != null) {
                    var amount = 0.0
                    var comboDesc = ""
                    var timestamp = System.currentTimeMillis()
                    for (item in details) {
                        amount += item.unitPrice * item.quantity
                    }
                    dmServiceListOrigin.amountCombo = amount
                    dmServiceListOrigin.comboId = dmServiceListOrigin.code + "*" + timestamp
                    details.forEachIndexed { index, item ->
                        comboDesc += if (index < details.size.minus(1)) {
                            "x" + StringExt.convertNumberToString(item.quantity * dmServiceListOrigin.quantity) + " " + item.name + "\n"
                        } else {
                            "x" + StringExt.convertNumberToString(item.quantity * dmServiceListOrigin.quantity) + " " + item.name
                        }
                    }
                    dmServiceListOrigin.comboDesc = comboDesc
                    mOrderModel.details.add(ConverUtil.convertServiceListToPromotion(dmServiceListOrigin))
                    for (item in details) {
                        item.comboId = dmServiceListOrigin.code + "*" + timestamp
                        item.quantity = item.quantity * dmServiceListOrigin.quantity
                        mOrderModel.details.add(ConverUtil.convertServiceListToPromotion(item))
                    }
                }
            } else {
                mOrderModel.details.add(ConverUtil.convertServiceListToPromotion(dmServiceListOrigin))
            }
        }
        var count = 1
        for (i in mOrderModel.details.indices) {
            if (DmServiceListOrigin.TYPE_COMBO == mOrderModel.details[i].serviceType || !mOrderModel.details[i].isCombo) {
                mOrderModel.details[i].position = count
                count += 1
            }
        }
    }

    fun OrderOnlineAutoPromotionToDetail(reponse: ArrayList<DmService>) {
        for (i in mOrderModel.details.indices) {
            for (dmService in reponse) {
                if (dmService.serviceCode == mOrderModel.details[i].serviceCode && !dmService.isCombo && !mOrderModel.details[i].isCombo) {
                    mOrderModel.details[i].amount = dmService.amount
                    mOrderModel.details[i].promotionName = dmService.promotionName
                    mOrderModel.details[i].dmPromotion = dmService.dmPromotion
                    mOrderModel.details[i].discountAmount = dmService.discountAmount
                    if (dmService.dmPromotion != null) {
                        mOrderModel.haveAutoPromotion = true
                    }
                }
            }
        }
    }

    //Locate Cart
    fun getCartLocate(): CartLocateModel {
        return dmCartLocate
    }
}