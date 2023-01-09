package com.example.tvshowaid.ui.home.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.tvshowaid.R
import com.example.tvshowaid.databinding.ActivityTvShowBinding
import com.example.tvshowaid.models.Episode
import com.example.tvshowaid.models.TvShowDetailed
import com.example.tvshowaid.repository.RetrofitInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception

val TAG = "TvShowActivity"

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding
    private lateinit var tvShowDetailed: TvShowDetailed
    private lateinit var tvShowViewModel: TvShowViewModel
    private var tvShowId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tvShowId= this.intent.getIntExtra("TVSHOWID",-1)
        tvShowViewModel = ViewModelProvider(this)[TvShowViewModel::class.java]

        loadDataFromAPI()

    }



    private fun setUpUi() {

        binding.tvShowActivityToolbar.title = tvShowDetailed.name
        setSupportActionBar(binding.tvShowActivityToolbar)


        Glide.with(this)
            .load(tvShowDetailed.image_path)
            .into(binding.tvShowActivityBannerDp)


        Glide.with(this)
            .load(tvShowDetailed.image_thumbnail_path)
            .into(binding.tvShowActivityBannerImage)


        setUpViewPager()

    }

    private fun loadDataFromAPI(){

        lifecycleScope.launchWhenCreated {

            val response = try {
                RetrofitInstance.api.getTvShowDetailById(tvShowId)
            }
            catch (e:Exception){
                e.printStackTrace()
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body()!=null){

                tvShowDetailed = response.body()!!.tvShow
                tvShowViewModel.updateEpisodeList(tvShowDetailed.episodes)

                setUpUi()
            }
            else{
                Log.e(TAG,"response was not successful")

            }
        }
    }

    private fun setUpViewPager(){
        binding.tvShowActivityViewpager.adapter = TvShowViewPagerAdapter(this)


        TabLayoutMediator(binding.tvShowActivityTablayout,binding.tvShowActivityViewpager){tab,position ->

            val tabs = listOf("About","Episodes")
            tab.text = tabs[position]

        }.attach()

    }
}