/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author
 */
public class DmStore implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6737882570791275775L;


    @SerializedName("_id")
    private String id;

    @SerializedName("storeId")
    private String storeID;

    @SerializedName("fb_store_id")
    private String fbStoreID;

    @SerializedName("companyId")
    private String companyId;


    @SerializedName("brandId")
    private String brandId;

    @SerializedName("active")
    private int active;

    @SerializedName("name")
    private String storeName = "";

    @SerializedName("city_id")
    private String cityId = "";

    @SerializedName("is_check")
    private boolean isCheck = false;


    @SerializedName("check_click_header")
    private boolean checkClickHeader = false;

    public String getCityId() {
        return cityId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public boolean isCheckClickHeader() {
        return checkClickHeader;
    }

    public void setCheckClickHeader(boolean checkClickHeader) {
        this.checkClickHeader = checkClickHeader;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getFbStoreID() {
        return fbStoreID;
    }

    public void setFbStoreID(String fbStoreID) {
        this.fbStoreID = fbStoreID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
