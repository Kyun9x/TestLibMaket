package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmHistoryOrderDetail;

import java.io.Serializable;

public class RestDmHistoryOrderOnline implements Serializable {

    @SerializedName("data")
    private DmHistoryOrderDetail data;

    public DmHistoryOrderDetail getData() {
        return data;
    }

    public void setData(DmHistoryOrderDetail data) {
        this.data = data;
    }
}
