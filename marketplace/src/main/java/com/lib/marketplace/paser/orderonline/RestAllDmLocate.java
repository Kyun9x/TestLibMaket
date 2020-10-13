package com.lib.marketplace.paser.orderonline;

import com.google.gson.annotations.SerializedName;
import com.lib.marketplace.model.orderonline.DmLocate;

import java.io.Serializable;
import java.util.ArrayList;

public class RestAllDmLocate implements Serializable {


    @SerializedName("data")
    private ArrayList<DmLocate> datas = null;


    public ArrayList<DmLocate> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DmLocate> datas) {
        this.datas = datas;
    }

}
