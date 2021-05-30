package com.aries.testkoanba.network.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("budget") val budget: String,
    @SerializedName("genres") val genres: ArrayList<Genre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: ArrayList<Product>,
    @SerializedName("production_countries") val production_countries: ArrayList<Country>,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("revenue") val revenue: String,
    @SerializedName("runtime") val runtime: String,
    @SerializedName("status") val status: String,
//    @SerializedName("Ratings") val ratings: ArrayList<Ratings>,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val vote_average: String

)