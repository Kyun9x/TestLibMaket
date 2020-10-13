package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmPaymentInfo implements Serializable {

    public static final String NAME_ZALOPAY = "ZaloPay";
    public static final String NAME_MOMO = "MoMo";

    @SerializedName("method")
    private String method = "";

    @SerializedName("note")
    private String note = "";

    @SerializedName("payAmount")
    private double payAmount;

    @SerializedName("payTime")
    private String payTime;

    @SerializedName("transId")
    private String transId;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
