package com.example.tvshowaid.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
class Progress(
    val userId:Int,
    @PrimaryKey(autoGenerate = true) val id:Int,
    val tvShowId:Int,val seasonCompleted:Int,
    val episodeCompleted:Int,
    val dateModified:Long) {
}