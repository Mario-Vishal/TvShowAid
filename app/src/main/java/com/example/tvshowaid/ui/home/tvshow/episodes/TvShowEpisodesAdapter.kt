package com.example.tvshowaid.ui.home.tvshow.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowaid.R
import com.example.tvshowaid.models.Episode

class TvShowEpisodesAdapter(private var list: ArrayList<Episode>):RecyclerView.Adapter<TvShowEpisodesAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private lateinit var episode: Episode


        fun bind(episode: Episode){

            this.episode = episode
            itemView.findViewById<TextView>(R.id.item_holder_episode_season_info).text = "Season "+ episode.season.toString() +" : Episode "+episode.episode
            itemView.findViewById<TextView>(R.id.item_holder_episode_date).text = episode.air_date
            itemView.findViewById<TextView>(R.id.item_holder_episode_name).text = episode.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_holder_episode,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun updateData(episodeList: ArrayList<Episode>){
        list = episodeList
        notifyDataSetChanged()
    }


}