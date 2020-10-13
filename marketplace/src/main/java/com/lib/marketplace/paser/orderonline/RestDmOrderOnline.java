package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmOrderOnline;

import java.io.Serializable;

public class RestDmOrderOnline implements Serializable {

    @SerializedName("data")
    private DmOrderOnline data = null;

    public DmOrderOnline getData() {
        return data;
    }

    public void setData(DmOrderOnline data) {
        this.data = data;
    }
}
