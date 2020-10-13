package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmQRCode;

import java.io.Serializable;

public class RestDmQRCode implements Serializable {

    @SerializedName("data")
    private DmQRCode data = null;

    public DmQRCode getData() {
        return data;
    }

    public void setData(DmQRCode data) {
        this.data = data;
    }
}
