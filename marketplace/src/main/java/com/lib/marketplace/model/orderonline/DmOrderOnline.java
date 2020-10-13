package com.lib.marketplace.model.orderonline;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.R;
import com.lib.marketplace.app.ApplicationIpos;

import java.io.Serializable;
import java.util.ArrayList;

public class DmOrderOnline implements Serializable {

    public static final String ZALOPAY = "ZALOPAY";
    public static final String MOMO = "MOMO_QR";
    public static final String COD = "COD";
    public static final String MERCHANTNAME = "IPOS.VN";
    public static final String MERCHANTCODE = "MOMOYOOI20200814";

    @SerializedName("companyId")
    private String companyId;

    @SerializedName("customerName")
    private String customerName;

    @SerializedName("contactId")
    private String contactId;

    @SerializedName("contactName")
    private String contactName;

    @SerializedName("contactPhone")
    private String contactPhone;

    @SerializedName("contactCompany")
    private String contactCompany;

    @SerializedName("contactTitle")
    private String contactTitle;

    @SerializedName("companyTaxCode")
    private String companyTaxCode;

    @SerializedName("companyTaxEmail")
    private String companyTaxEmail;

    @SerializedName("companyFullAddress")
    private String companyFullAddress;

    @SerializedName("brandId")
    private String brandId;

    @SerializedName("storeName")
    private String storeName;
    @SerializedName("productCode")
    private String productCode = "FABI";

    @SerializedName("storeId")
    private String storeId;

    @SerializedName("amount")
    private double amount;

    @SerializedName("orderCode")
    private String orderCode;

    @SerializedName("status")
    private String status;
    @SerializedName("invoice_id")
    private String invoice_id;

    @SerializedName("status_name")
    private String statusName;

    @SerializedName("requestInvoice")
    private int requestInvoice = 0;

    @SerializedName("details")
    private ArrayList<DmService> details = new ArrayList<>();

    private ArrayList<DmServiceListOrigin> originDetails = new ArrayList<>();


    @SerializedName("deliveryInfo")
    private DmDeliveryInfo dmDeliveryInfo;

    @SerializedName("voucher")
    private DmVoucher dmVoucher;

    @SerializedName("discountAmount")
    private double discountAmount = 0;

    @SerializedName("paymentInfo")
    private DmPaymentInfo dmPaymentInfo;


    @SerializedName("customerNote")
    private String customerNote;
    @SerializedName("createdTime")
    private String createdTime;

    public Boolean isHaveAutoPromotion = false;

    @SerializedName("chargeZaloPayResponse")
    private DmChargeZaloPayResponse dmChargeZaloPayResponse;

    private String orderType = "";

    public DmChargeZaloPayResponse getDmChargeZaloPayResponse() {
        return dmChargeZaloPayResponse;
    }

    public void setDmChargeZaloPayResponse(DmChargeZaloPayResponse dmChargeZaloPayResponse) {
        this.dmChargeZaloPayResponse = dmChargeZaloPayResponse;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }


    public DmPaymentInfo getDmPaymentInfo() {
        return dmPaymentInfo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setDmPaymentInfo(DmPaymentInfo dmPaymentInfo) {
        this.dmPaymentInfo = dmPaymentInfo;
    }

    public DmVoucher getDmVoucher() {
        return dmVoucher;
    }

    public void setDmVoucher(DmVoucher dmVoucher) {
        this.dmVoucher = dmVoucher;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public DmOrderOnline() {

    }

    public int getRequestInvoice() {
        return requestInvoice;
    }

    public void setRequestInvoice(int requestInvoice) {
        this.requestInvoice = requestInvoice;
    }

    public String getStatusName(Context context) {
        if (DmStatusOrder.TYPE_PAYING.equals(status)) {
            statusName = context.getString(R.string.cho_thanh_toan);
        } else if (DmStatusOrder.TYPE_PENDING.equals(status)) {
            statusName = context.getString(R.string.da_thanh_toan);
        } else if (DmStatusOrder.TYPE_RECEIVED.equals(status)) {
            statusName = context.getString(R.string.da_tiep_nhan);
        } else if (DmStatusOrder.TYPE_PROCESSED.equals(status)) {
            statusName = context.getString(R.string.da_xu_ly_Xong);
        } else if (DmStatusOrder.TYPE_SHIPPING.equals(status)) {
            statusName = context.getString(R.string.dnag_giao_hang);
        } else if (DmStatusOrder.TYPE_COMPLETED.equals(status)) {
            statusName = context.getString(R.string.hoan_thanh);
        } else if (DmStatusOrder.TYPE_CANCELED.equals(status)) {
            statusName = context.getString(R.string.bi_huy);
        }
        return statusName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactCompany() {
        return contactCompany;
    }

    public void setContactCompany(String contactCompany) {
        this.contactCompany = contactCompany;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getCompanyTaxCode() {
        return companyTaxCode;
    }

    public void setCompanyTaxCode(String companyTaxCode) {
        this.companyTaxCode = companyTaxCode;
    }

    public String getCompanyTaxEmail() {
        return companyTaxEmail;
    }

    public void setCompanyTaxEmail(String companyTaxEmail) {
        this.companyTaxEmail = companyTaxEmail;
    }

    public String getCompanyFullAddress() {
        return companyFullAddress;
    }

    public void setCompanyFullAddress(String companyFullAddress) {
        this.companyFullAddress = companyFullAddress;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<DmService> getCart() {
        ArrayList<DmService> data = new ArrayList<>();
        for (DmService item : getDetails()) {
            if (item.getPosition() != 0) {
                data.add(item);
            }
        }
        return data;
    }

    public ArrayList<DmService> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DmService> details) {
        this.details = details;
    }

    public DmDeliveryInfo getDmDeliveryInfo() {
        return dmDeliveryInfo;
    }

    public void setDmDeliveryInfo(DmDeliveryInfo dmDeliveryInfo) {
        this.dmDeliveryInfo = dmDeliveryInfo;
    }

    public ArrayList<DmServiceListOrigin> getOriginDetails() {
        return originDetails;
    }

    public void setOriginDetails(ArrayList<DmServiceListOrigin> originDetails) {
        this.originDetails = originDetails;
    }

    public Boolean getHaveAutoPromotion() {
        return isHaveAutoPromotion;
    }

    public void setHaveAutoPromotion(Boolean haveAutoPromotion) {
        isHaveAutoPromotion = haveAutoPromotion;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
