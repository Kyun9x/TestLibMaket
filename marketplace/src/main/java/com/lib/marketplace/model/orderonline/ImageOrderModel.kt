package com.ipos.iposmanage.model.orderonline

import java.io.Serializable

data class ImageOrderModel(
        var img_url_thumb: String? = null,
        var img_url: String? = null
) : Serializable