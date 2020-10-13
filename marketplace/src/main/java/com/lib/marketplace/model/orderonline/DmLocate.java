package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DmLocate implements Serializable {

    public static String CODE_VN = "VNN";

    @SerializedName("_id")
    private String id_ = "";

    @SerializedName("code")
    private String code = "";

    @SerializedName("type")
    private String type = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("city")
    private ArrayList<DmCityOd> dmCities = new ArrayList<>();

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DmCityOd> getDmCities() {
        return dmCities;
    }

    public void setDmCities(ArrayList<DmCityOd> dmCities) {
        this.dmCities = dmCities;
    }
}
