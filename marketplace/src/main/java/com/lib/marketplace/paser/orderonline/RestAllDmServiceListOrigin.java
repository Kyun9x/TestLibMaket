package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.ipos.iposmanage.model.orderonline.DmServiceListOrigin;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmServiceListOrigin implements Serializable {
    @SerializedName("data")
    private ArrayList<DmServiceListOrigin> objects = null;

    public ArrayList<DmServiceListOrigin> getData() {
        return objects;
    }

    public void setObjects(ArrayList<DmServiceListOrigin> objects) {
        this.objects = objects;
    }

}
