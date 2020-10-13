package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmQRCode implements Serializable {

    @SerializedName("appid")
    private String appid = "";

    @SerializedName("orderurl")
    private String orderurl = "";

    @SerializedName("returncode")
    private int returncode;

    @SerializedName("returnmessage")
    private String returnmessage;

    @SerializedName("zptranstoken")
    private String zptranstoken;

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("orderId")
    private String orderId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOrderurl() {
        return orderurl;
    }

    public void setOrderurl(String orderurl) {
        this.orderurl = orderurl;
    }

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }

    public String getZptranstoken() {
        return zptranstoken;
    }

    public void setZptranstoken(String zptranstoken) {
        this.zptranstoken = zptranstoken;
    }
}
