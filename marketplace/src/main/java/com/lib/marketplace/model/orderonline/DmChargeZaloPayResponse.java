package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmChargeZaloPayResponse implements Serializable {

    public static final String STATUS_ERROR_ORDER_CODE = "-49";

    @SerializedName("returncode")
    private String returncode;

    @SerializedName("returnmessage")
    private String returnmessage;

    @SerializedName("zptransid")
    private String zptransid;

    @SerializedName("amount")
    private String amount;

    @SerializedName("discountamount")
    private String discountamount;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }

    public String getZptransid() {
        return zptransid;
    }

    public void setZptransid(String zptransid) {
        this.zptransid = zptransid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(String discountamount) {
        this.discountamount = discountamount;
    }
}
