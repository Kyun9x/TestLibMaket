package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmOrderOnline;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmOrderOnline implements Serializable {

    @SerializedName("data")
    private ArrayList<DmOrderOnline> data = new ArrayList<>();

    public ArrayList<DmOrderOnline> getData() {
        return data;
    }

    public void setData(ArrayList<DmOrderOnline> data) {
        this.data = data;
    }
}
