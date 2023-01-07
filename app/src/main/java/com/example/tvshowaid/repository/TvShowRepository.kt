package com.example.tvshowaid.repository

import com.example.tvshowaid.models.TvShow
import com.example.tvshowaid.models.TvShowDetailed

interface TvShowRepository {

    suspend fun getDefaultTvShowsByPage(page:Int):List<TvShow>

    fun getTvShowInfoById():TvShowDetailed

}