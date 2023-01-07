package com.example.tvshowaid.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    val api: TvShowApi by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build()

        Retrofit.Builder()
            .baseUrl("https://www.episodate.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(TvShowApi::class.java)
    }
}