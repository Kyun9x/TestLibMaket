package com.lib.marketplace.paser.orderonline;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmService;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmCheckVoucherToServer implements Serializable {
    @SerializedName("data")
    private ArrayList<DmService> objects = null;

    @SerializedName("voucherCode")
    private String voucherCode;

    @SerializedName("productCode")
    private String productCode = "FABI";

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }


    public ArrayList<DmService> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<DmService> objects) {
        this.objects = objects;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}

