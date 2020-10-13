package com.ipos.iposmanage.model.marrketplace

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedbackModel(
        @SerializedName("feedback_title") var feedback_title: String? = "",
        @SerializedName("feedback_content") var feedback_content: String? = "",
        @SerializedName("customer_phone") var customer_phone: String? = "",
        @SerializedName("customer_name") var customer_name: String? = ""
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class FeedbackModelData(
        @SerializedName("data") var data: FeedbackModel? = null
) : Serializable {

    fun toJson(): String {
        return Gson().toJson(this)
    }
}