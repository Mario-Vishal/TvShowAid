package com.example.tvshowaid.models

data class TvShowApiResponse(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: ArrayList<TvShow>
)