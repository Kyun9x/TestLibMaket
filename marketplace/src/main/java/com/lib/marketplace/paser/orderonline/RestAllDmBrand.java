package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmBrand;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmBrand implements Serializable {


    @SerializedName("data")
    private ArrayList<DmBrand> datas = null;


    public ArrayList<DmBrand> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DmBrand> datas) {
        this.datas = datas;
    }

}
