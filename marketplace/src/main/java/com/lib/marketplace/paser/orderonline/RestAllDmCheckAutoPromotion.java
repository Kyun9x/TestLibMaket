package com.lib.marketplace.paser.orderonline;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmService;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmCheckAutoPromotion implements Serializable {
    @SerializedName("data")
    private ArrayList<DmService> datas = null;

    @SerializedName("voucherCode")
    private String voucherCode;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public ArrayList<DmService> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DmService> datas) {
        this.datas = datas;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
