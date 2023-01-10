package com.example.tvshowaid.database.dao

import androidx.room.*
import com.example.tvshowaid.database.WatchList


@Dao
interface WatchListDao {

    @Insert()
    fun addToWatchList(watchList: WatchList)

    @Delete()
    fun removeFromWatchList(watchList: WatchList)

    @Query("SELECT * from watchlist where userId=id")
    fun getWatchListByUserId(id: Int):List<WatchList>

}