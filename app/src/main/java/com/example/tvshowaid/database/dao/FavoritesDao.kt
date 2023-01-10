package com.example.tvshowaid.database.dao

import androidx.room.*
import com.example.tvshowaid.database.Favorites


@Dao
interface FavoritesDao {

    @Insert()
    fun addToFavorites(favorites: Favorites)

    @Delete()
    fun removeFromFavorites(favorites: Favorites)

    @Query("select * from favorites where userId=id")
    fun getFavoritesByUserId(id:Int):List<Favorites>
}