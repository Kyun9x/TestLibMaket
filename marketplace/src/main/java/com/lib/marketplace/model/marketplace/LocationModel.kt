package com.ipos.iposmanage.model.marrketplace

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocationModel(
        @SerializedName("id") var id: String? = null,
        @SerializedName("location_name") var location_name: String? = null,
        @SerializedName("company_id") var company_id: String? = null,
        @SerializedName("precinct_uid") var precinct_uid: String? = null,
        @SerializedName("district_uid") var district_uid: String? = null,
        @SerializedName("country_uid") var country_uid: String? = null,
        @SerializedName("city_uid") var city_uid: String? = null,
        @SerializedName("precinct") var precinct: PrecinctModel? = null,
        @SerializedName("district") var district: DistrictModel? = null,
        @SerializedName("city") var city: CityModel? = null,
       var selected: Boolean? = false
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class PrecinctModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("precinct_name") var precinct_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class DistrictModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("fb_id") var fb_id: String? = "",
        @SerializedName("district_name") var district_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class CityModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("fb_id") var fb_id: String? = "",
        @SerializedName("city_name") var city_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class CountryModel(
        @SerializedName("id") var id: String? = "",
        @SerializedName("country_name") var country_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class LocationModelData(
        @SerializedName("data") var data: ArrayList<LocationModel> = ArrayList()
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class LocationModelDataObject(
        @SerializedName("data") var data: LocationModel? = null
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}