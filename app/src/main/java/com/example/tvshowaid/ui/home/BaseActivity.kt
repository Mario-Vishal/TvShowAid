package com.example.tvshowaid.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tvshowaid.R
import com.example.tvshowaid.databinding.ActivityBaseBinding
import com.example.tvshowaid.ui.home.account.AccountFragment
import com.example.tvshowaid.ui.home.tvshows.TvShowsFragment
import com.example.tvshowaid.ui.home.watchlist.WatchListFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBaseBinding
    private var currentFragment :Int = BaseActivity.TVSHOWFRAGMENT

    companion object {

        val TVSHOWFRAGMENT = 0
        val WATCHLISTFRAGMENT = 1
        val ACCOUNTFRAGMENT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpBtmNav()
        openFragment(TvShowsFragment())
    }

    private fun setUpBtmNav() {

        binding.baseBottomNavMenuView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.base_bottom_nav_menu_tv_shows -> {
                    if (currentFragment!= TVSHOWFRAGMENT) openFragment(TvShowsFragment())
                    true
                }
                R.id.base_bottom_nav_menu_watchlist -> {
                    if (currentFragment!= WATCHLISTFRAGMENT) openFragment(WatchListFragment())
                    true
                }
                R.id.base_bottom_nav_menu_account -> {
                    if(currentFragment!= ACCOUNTFRAGMENT)openFragment(AccountFragment())
                    true
                }
                else -> {
                    openFragment(TvShowsFragment())
                    true
                }
            }
        }
    }

    private fun openFragment(fragment:Fragment){

        currentFragment = when(fragment){
            TvShowsFragment() -> TVSHOWFRAGMENT
            WatchListFragment()-> WATCHLISTFRAGMENT
            AccountFragment() -> ACCOUNTFRAGMENT
            else -> TVSHOWFRAGMENT
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.baseFragmentContainer.id,fragment)
        transaction.commit()
    }


}