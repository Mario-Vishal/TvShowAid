package com.example.tvshowaid.ui.home.tvshow

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tvshowaid.ui.home.tvshow.about.TvShowAboutFragment
import com.example.tvshowaid.ui.home.tvshow.episodes.TvShowEpisodesFragment

class TvShowViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {

        return 2
    }

    override fun createFragment(position: Int): Fragment {

        when(position){

            0 -> return TvShowAboutFragment()
            1 -> return TvShowEpisodesFragment()
            else -> return TvShowAboutFragment()
        }
    }
}