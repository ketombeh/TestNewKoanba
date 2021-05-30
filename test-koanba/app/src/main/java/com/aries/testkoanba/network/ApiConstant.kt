package com.aries.testkoanba.network

class ApiConstant{
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val NOW_PLAYING = BASE_URL + "now_playing"
        const val POPULAR = BASE_URL + "popular"
        const val TOP_RATE = BASE_URL + "top_rated"
        const val DETAIL = BASE_URL + "{movie_id}"
        const val API_KEY = "3cc271ef45cb20dea870a11f41372e9a"
    }
}