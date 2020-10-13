package com.lib.marketplace.model.orderonline;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmCallBackMoMo implements Serializable {

    @SerializedName("transId")
    private String transId = "";

    @SerializedName("phoneNumber")
    private String phoneNumber = "";

    @SerializedName("data")
    private String data = "";

    @SerializedName("amount")
    private int amount;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
