package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmPromotion implements Serializable {


    @SerializedName("name")
    private String name = "";

    @SerializedName("productCode")
    private String productCode = "";

    @SerializedName("serviceCode")
    private String serviceCode = "";

    @SerializedName("fromQuantity")
    private double fromQuantity;

    @SerializedName("toQuantity")
    private double toQuantity;

    @SerializedName("fromAmount")
    private double fromAmount;

    @SerializedName("toAmount")
    private double toAmount;

    @SerializedName("dateStart")
    private String dateStart = "";

    @SerializedName("dateEnd")
    private String dateEnd = "";

    @SerializedName("limitQuantity")
    private double limitQuantity;

    @SerializedName("quantityRemain")
    private double quantityRemain;

    @SerializedName("discountPercent")
    private double discountPercent;

    @SerializedName("discountAmount")
    private double discountAmount;

    @SerializedName("extraQuantity")
    private double extraQuantity;

    @SerializedName("campaignId")
    private String campaignId = "";

    @SerializedName("_id")
    private String _id = "";

    @SerializedName("status")
    private String status = "";

    @SerializedName("createdBy")
    private String createdBy = "";

    @SerializedName("createdTime")
    private String createdTime = "";

    @SerializedName("updatedTime")
    private String updatedTime = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public double getFromQuantity() {
        return fromQuantity;
    }

    public void setFromQuantity(double fromQuantity) {
        this.fromQuantity = fromQuantity;
    }

    public double getToQuantity() {
        return toQuantity;
    }

    public void setToQuantity(double toQuantity) {
        this.toQuantity = toQuantity;
    }

    public double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public double getToAmount() {
        return toAmount;
    }

    public void setToAmount(double toAmount) {
        this.toAmount = toAmount;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(double limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public double getQuantityRemain() {
        return quantityRemain;
    }

    public void setQuantityRemain(double quantityRemain) {
        this.quantityRemain = quantityRemain;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getExtraQuantity() {
        return extraQuantity;
    }

    public void setExtraQuantity(double extraQuantity) {
        this.extraQuantity = extraQuantity;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
