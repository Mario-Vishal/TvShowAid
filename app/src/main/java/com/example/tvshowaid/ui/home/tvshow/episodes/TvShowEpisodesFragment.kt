package com.example.tvshowaid.ui.home.tvshow.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowaid.R
import com.example.tvshowaid.models.Episode
import com.example.tvshowaid.ui.home.tvshow.TvShowViewModel


class TvShowEpisodesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowEpisodesAdapter
    private lateinit var episodes:ArrayList<Episode>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_show_episodes, container, false)

        recyclerView = view.findViewById(R.id.episodes_fragment_rv)
        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        episodes = arrayListOf()

        adapter = TvShowEpisodesAdapter(episodes)

        viewModel.episodes.observe( viewLifecycleOwner, Observer { episodes ->
            this.episodes = episodes
            adapter.updateData(this.episodes)
        })
//        adapter.updateData(this.episodes)
//        Toast.makeText(requireContext(),episodes.size.toString(),Toast.LENGTH_SHORT).show()



        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL,false)

        return view
    }



}