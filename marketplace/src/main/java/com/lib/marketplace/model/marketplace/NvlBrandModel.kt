package com.ipos.iposmanage.model.marrketplace

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NvlBrandModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("brand_id") var brand_id: String? = "",
        @SerializedName("brand_name") var brand_name: String? = "",
        @SerializedName("company_id") var company_id: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class NvlBrandModelData(
        @SerializedName("data") var data: ArrayList<NvlBrandModel>? = null
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}