package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DmHistoryOrderDetail implements Serializable {


    @SerializedName("count")
    private String count = "";

    @SerializedName("numPerPage")
    private String numPerPage;

    @SerializedName("data")
    private ArrayList<DmOrderOnline> mDatas = new ArrayList<>();

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(String numPerPage) {
        this.numPerPage = numPerPage;
    }

    public ArrayList<DmOrderOnline> getmDatas() {
        return mDatas;
    }

    public void setmDatas(ArrayList<DmOrderOnline> mDatas) {
        this.mDatas = mDatas;
    }
}
