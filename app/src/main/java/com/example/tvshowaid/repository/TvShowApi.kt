package com.example.tvshowaid.repository

import com.example.tvshowaid.models.TvShowApiResponse
import com.example.tvshowaid.models.TvShowDetailedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET("/api/most-popular")
    suspend fun getTvShows(@Query("page") page : Int): Response<TvShowApiResponse>

    @GET("/api/show-details")
    suspend fun getTvShowDetailById(@Query("q")id:Int):Response<TvShowDetailedResponse>




}