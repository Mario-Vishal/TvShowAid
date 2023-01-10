package com.example.tvshowaid.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val username:String,
    val password:String,
    val emailId:String,
    val dateOfSignUp:Long)