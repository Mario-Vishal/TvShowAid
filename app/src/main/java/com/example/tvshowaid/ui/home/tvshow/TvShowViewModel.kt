package com.example.tvshowaid.ui.home.tvshow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowaid.models.Episode
import com.example.tvshowaid.models.TvShowDetailed

class TvShowViewModel(application: Application):AndroidViewModel(application) {

    val episodes = MutableLiveData<ArrayList<Episode>>().apply {
        value = arrayListOf()
    }

    fun updateEpisodeList(episodes : ArrayList<Episode>){

        this.episodes.value = episodes
    }
}