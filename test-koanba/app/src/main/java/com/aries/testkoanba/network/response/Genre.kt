package com.aries.testkoanba.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
    var itemType: Int = 1, // type item : 0 typefooter

    @SerializedName("name")
    @Expose
    var name: String? = null
)