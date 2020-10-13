package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DmVoucher implements Serializable {

    public static final String TYPE_DISCOUNT = "DISCOUNT";

    @SerializedName("voucherCode")
    private String voucherCode;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("promoVoucherId")
    private String promoVoucherId;

    @SerializedName("promoCampaignId")
    private String promoCampaignId;

    @SerializedName("promoCampaignName")
    private String promoCampaignName;

    @SerializedName("receiver")
    private String receiver;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("inputType")
    private String inputType;

    @SerializedName("_id")
    private String _id;

    @SerializedName("status")
    private String status;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("createdTime")
    private String createdTime;

    @SerializedName("discountAmount")
    private double discountAmount;


    @SerializedName("usedDiscountAmount")
    private double usedDiscountAmount;


    @SerializedName("gifts")
    private ArrayList<DmService> gifts = new ArrayList<>();

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getUsedDiscountAmount() {
        return usedDiscountAmount;
    }

    public void setUsedDiscountAmount(double usedDiscountAmount) {
        this.usedDiscountAmount = usedDiscountAmount;
    }

    public ArrayList<DmService> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<DmService> gifts) {
        this.gifts = gifts;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPromoVoucherId() {
        return promoVoucherId;
    }

    public void setPromoVoucherId(String promoVoucherId) {
        this.promoVoucherId = promoVoucherId;
    }

    public String getPromoCampaignId() {
        return promoCampaignId;
    }

    public void setPromoCampaignId(String promoCampaignId) {
        this.promoCampaignId = promoCampaignId;
    }

    public String getPromoCampaignName() {
        return promoCampaignName;
    }

    public void setPromoCampaignName(String promoCampaignName) {
        this.promoCampaignName = promoCampaignName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
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
}
