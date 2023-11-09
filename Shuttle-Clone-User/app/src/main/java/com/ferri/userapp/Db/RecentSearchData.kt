package com.ferri.userapp.Db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_search_table")
data class RecentSearchData(
    val pickUpLat: String,
    val pickUpLng: String,
    val dropLat: String,
    val dropLng: String,
    val pickUpAddress: String,
    val dropAddress: String,
    val date: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)