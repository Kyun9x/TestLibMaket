package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DmStatusOrder implements Serializable {

    public static final String TYPE_PAYING = "PAYING";
    public static final String TYPE_PENDING = "PENDING";
    public static final String TYPE_RECEIVED = "RECEIVED";
    public static final String TYPE_PROCESSED = "PROCESSED";
    public static final String TYPE_SHIPPING = "SHIPPING";
    public static final String TYPE_COMPLETED = "COMPLETED";
    public static final String TYPE_CANCELED = "CANCELED";


    @SerializedName("type")
    private String type = TYPE_PAYING;

    @SerializedName("name")
    private String name = "";

    @SerializedName("is_checked_status")
    private boolean isCheckedStatus = false;

    @SerializedName("is_first_position")
    private boolean isFirstPosition = false;

    @SerializedName("is_end_position")
    private boolean isEndPosition = false;

    @SerializedName("is_corect_position")
    private boolean isCorectPosition = false;

    public DmStatusOrder(String type, String name, boolean isCheckedStatus, boolean isFirstPosition, boolean isEndPosition, boolean isCorectPosition) {
        this.type = type;
        this.name = name;
        this.isCheckedStatus = isCheckedStatus;
        this.isFirstPosition = isFirstPosition;
        this.isEndPosition = isEndPosition;
        this.isCorectPosition = isCorectPosition;
    }

    public boolean isCorectPosition() {
        return isCorectPosition;
    }

    public void setCorectPosition(boolean corectPosition) {
        isCorectPosition = corectPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCheckedStatus() {
        return isCheckedStatus;
    }

    public void setCheckedStatus(boolean checkedStatus) {
        isCheckedStatus = checkedStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirstPosition() {
        return isFirstPosition;
    }

    public void setFirstPosition(boolean firstPosition) {
        isFirstPosition = firstPosition;
    }

    public boolean isEndPosition() {
        return isEndPosition;
    }

    public void setEndPosition(boolean endPosition) {
        isEndPosition = endPosition;
    }
}
