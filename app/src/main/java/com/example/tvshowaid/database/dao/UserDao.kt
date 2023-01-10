package com.example.tvshowaid.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.tvshowaid.database.User


@Dao
interface UserDao {

    @Insert()
    fun insertUser(user: User)

    @Update()
    fun updateUser(user: User)

    @Delete()
    fun deleteUser(user: User)
    
}