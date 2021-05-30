package com.aries.testkoanba.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    var itemType: Int = 1, // type item : 0 typefooter

    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("release_date")
    @Expose
    var release_date: String? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("Type")
    @Expose
    var type: String? = null,
    @SerializedName("poster_path")
    @Expose
    var poster_path: String? = null
)