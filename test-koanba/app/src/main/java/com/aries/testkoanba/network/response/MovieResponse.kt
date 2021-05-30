package com.aries.testkoanba.network.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val results: ArrayList<Movie>
)