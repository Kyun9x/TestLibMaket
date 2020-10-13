package com.ipos.iposmanage.model.marrketplace

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TagsModel(
        @SerializedName("tag_id") var tag_id: String? = "",
        @SerializedName("tag_name") var tag_name: String? = "",
        @SerializedName("id") var id: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class TagsModelData(
        @SerializedName("data") var data: ArrayList<TagsModel> = ArrayList()
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}