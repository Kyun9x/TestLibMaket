package com.lib.marketplace.model.orderonline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DmCityOd implements Serializable {

    @SerializedName("code")
    private String code = "";

    @SerializedName("type")
    private String type = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("district")
    private ArrayList<DmDistrict> dmDistricts = new ArrayList<>();

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

    public ArrayList<DmDistrict> getDmDistricts() {
        return dmDistricts;
    }

    public void setDmDistricts(ArrayList<DmDistrict> dmDistricts) {
        this.dmDistricts = dmDistricts;
    }
}
