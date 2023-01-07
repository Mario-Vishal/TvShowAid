package com.example.tvshowaid.ui.home.tvshows

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowaid.R
import com.example.tvshowaid.models.TvShow
import com.example.tvshowaid.repository.RetrofitInstance
import com.example.tvshowaid.ui.home.tvshow.TvShowActivity
import retrofit2.HttpException
import java.io.IOException

private const val TAG="BASE_ACTIVITY"


class TvShowsFragment : Fragment(),TvShowMainAdapter.clickListener {

    lateinit private var adapter: TvShowMainAdapter
    lateinit private var recyclerView: RecyclerView
    lateinit private var progressCircular : ProgressBar
    lateinit private var progressCircularBottom: ProgressBar
    private var isScrolling:Boolean = false
    private var currentPage=1
    private var alreadyScrolled:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_shows, container, false)

        recyclerView = view.findViewById(R.id.tv_show_rv)
        progressCircular = view.findViewById(R.id.progress_circular)
        progressCircularBottom = view.findViewById(R.id.progress_circular_bottom)

        adapter = TvShowMainAdapter(ArrayList(),this)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = adapter

        loadData(true)

        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItems = recyclerView.layoutManager!!.childCount
                val totalItems = recyclerView.layoutManager!!.itemCount
                val scrolledItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if(isScrolling && (currentItems + scrolledItems == totalItems)){

//                    progressCircularBottom.visibility =View.VISIBLE

                    if(alreadyScrolled==false){
                        loadData()
                        alreadyScrolled=true
                    }
                    println("already scrolled : $alreadyScrolled")
                    isScrolling=false
                }
                else{

                }
            }
        })

        return view
    }

    fun loadData(progressBarEnabled:Boolean=false){

        println("Current Page:  $currentPage")
        lifecycleScope.launchWhenCreated {

            if(progressBarEnabled) progressCircular.visibility = View.VISIBLE

            val response = try {

                RetrofitInstance.api.getTvShows(currentPage)
            } catch (e: IOException){
                Log.e(TAG,"IOexception")
                e.printStackTrace()
                if(progressBarEnabled)progressCircular.visibility = View.GONE

                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG,"HTTPexception")
                if(progressBarEnabled)progressCircular.visibility = View.GONE
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body()!=null){
                adapter.setData(response.body()!!.tv_shows)
                currentPage+=1
                println("${response.body()!!.tv_shows.size} ${response.body()!!.tv_shows[0].name}")
            }
            else{
                Log.e(TAG,"response was not successful")
            }

            if(progressBarEnabled)progressCircular.visibility = View.GONE
            alreadyScrolled=false

        }
    }

    override fun onClick(tvShow: TvShow) {

        startActivity(
            Intent(requireContext(),TvShowActivity::class.java).putExtra("TVSHOWID",tvShow.id)

        )
        Toast.makeText(requireContext(),"${tvShow.name}",Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(tvShow: TvShow) {
        TODO("Not yet implemented")
    }


}