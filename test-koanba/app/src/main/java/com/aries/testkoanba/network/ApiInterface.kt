package com.aries.testkoanba.network

import com.aries.testkoanba.network.response.MovieDetailResponse
import com.aries.testkoanba.network.response.MovieResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConstant.NOW_PLAYING) // only show now playing
    fun getMoviesNowPlaying(
        @Query("api_key") api_key: String,
        @Query("language") lenguange: String,
        @Query("page") page: String
    ): Observable<Response<MovieResponse>>

    @GET(ApiConstant.POPULAR) // only show now playing
    fun getMoviesPopular(
        @Query("api_key") api_key: String,
        @Query("language") lenguange: String,
        @Query("page") page: String
    ): Observable<Response<MovieResponse>>

    @GET(ApiConstant.TOP_RATE) // only show now playing
    fun getMoviesTopRated(
        @Query("api_key") api_key: String,
        @Query("language") lenguange: String,
        @Query("page") page: String
    ): Observable<Response<MovieResponse>>

    @GET(ApiConstant.DETAIL) // only show now playing
    fun getDetailMovies(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
        @Query("language") lenguange: String
    ): Observable<Response<MovieDetailResponse>>
}