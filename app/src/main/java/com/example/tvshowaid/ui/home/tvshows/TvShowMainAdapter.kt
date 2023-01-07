package com.example.tvshowaid.ui.home.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowaid.R
import com.example.tvshowaid.models.TvShow

class TvShowMainAdapter(var list:ArrayList<TvShow>,val onClickListener: TvShowMainAdapter.clickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    interface clickListener {

        fun onClick(tvShow: TvShow)
        fun onLongClick(tvShow: TvShow)
    }


    private class ItemViewHolder(itemView: View,val clickListener: clickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener,View.OnLongClickListener {

        val tvShowTitle: TextView = itemView.findViewById(R.id.tv_show_title)
        val tvShowImage : ImageView = itemView.findViewById(R.id.tv_show_image)

        lateinit private var tvShow: TvShow


        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bind(tvShow: TvShow){
            this.tvShow = tvShow
            itemView.findViewById<TextView>(R.id.tv_show_title).text = this.tvShow.name
            val imgView = itemView.findViewById<ImageView>(R.id.tv_show_image)
            Glide.with(itemView.context).load(this.tvShow.image_thumbnail_path)
                .into(imgView)
        }

        override fun onClick(v: View?) {

            clickListener.onClick(tvShow)

        }

        override fun onLongClick(v: View?): Boolean {

            clickListener.onLongClick(tvShow)
            return true
        }


    }

    private class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val progressBarBottom : ProgressBar = itemView.findViewById(R.id.item_progress_bar)

    }


    fun setData(list: ArrayList<TvShow>){

        if(this.list.isNotEmpty())this.list.removeLast()

        this.list.addAll(list)
        this.list.add(TvShow())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if(viewType==VIEW_TYPE_ITEM){
            return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow,parent,false),
                onClickListener
            )

        }
        else {
            return LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_circular_loading,parent,false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(list[position].id==-1) println("last null item")
        if(list[position].id==-1)return VIEW_TYPE_LOADING
        else return VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ItemViewHolder){
            holder.bind(list[position])
        }


    }


}