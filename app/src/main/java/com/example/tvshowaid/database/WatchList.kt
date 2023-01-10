package com.example.tvshowaid.database


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "watchlist",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class WatchList(val userId:Int, @PrimaryKey(autoGenerate = true) val id:Int, val tvShowId:Int, val dateAdded:Long) {
}