package com.ipos.iposmanage.model.marrketplace

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("city_id") var city_id: String? = "",
        @SerializedName("city_name") var city_name: String? = "",
        @SerializedName("district_id") var district_id: String? = "",
        @SerializedName("district_name") var district_name: String? = "",
        @SerializedName("precinct_id") var precinct_id: String? = "",
        @SerializedName("fb_id") var fb_id: String? = "",
        @SerializedName("precinct_name") var precinct_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class AddressModelData(
        @SerializedName("data") var data: ArrayList<AddressModel> = ArrayList()
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}