package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmStore;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmStore implements Serializable {


    @SerializedName("data")
    private ArrayList<DmStore> datas = null;


    public ArrayList<DmStore> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DmStore> datas) {
        this.datas = datas;
    }

}
