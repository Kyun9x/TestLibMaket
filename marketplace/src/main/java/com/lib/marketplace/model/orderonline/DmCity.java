package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by NgocLe on 09/23/2019.
 */
public class DmCity implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("city_id")
    private String cityID;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("is_check")
    private boolean isCheck = false;

    @SerializedName("stores")
    private ArrayList<DmStore> stores = new ArrayList<>();

    @SerializedName("check_click_header")
    private boolean checkClickHeader = false;

    public boolean isCheckClickHeader() {
        return checkClickHeader;
    }

    public void setCheckClickHeader(boolean checkClickHeader) {
        this.checkClickHeader = checkClickHeader;
    }

    public DmCity() {

    }

    public DmCity(String id, String cityID, String cityName, ArrayList<DmStore> stores) {
        this.id = id;
        this.cityID = cityID;
        this.cityName = cityName;
        this.stores = stores;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<DmStore> getStores() {
        return stores;
    }

    public void setStores(ArrayList<DmStore> stores) {
        this.stores = stores;
    }
}

