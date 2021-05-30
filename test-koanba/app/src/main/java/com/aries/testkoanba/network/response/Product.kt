package com.aries.testkoanba.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    var itemType: Int = 1, // type item : 0 typefooter

    @SerializedName("logo_path")
    @Expose
    var logo_path: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)