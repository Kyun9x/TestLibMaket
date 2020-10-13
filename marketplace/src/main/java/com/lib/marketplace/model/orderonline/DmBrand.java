package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DmBrand implements Serializable {

    @SerializedName("id")
    private String brandUid;

    @SerializedName("brandId")
    private String brandId;

    @SerializedName("name")
    private String brandName;

    @SerializedName("cities")
    private ArrayList<DmCity> cities = new ArrayList<>();

    @SerializedName("check")
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public ArrayList<DmCity> getCities() {
        return cities;
    }

    public void setCities(ArrayList<DmCity> cities) {
        this.cities = cities;
    }

    public String getBrandUid() {
        return brandUid;
    }

    public void setBrandUid(String brandUid) {
        this.brandUid = brandUid;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
