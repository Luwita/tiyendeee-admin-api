package com.ferri.userapp.Db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecentSearchDao {

    @Insert
    fun insert(recentSearchData: RecentSearchData)

    @Update
    fun update(recentSearchData: RecentSearchData)

    @Delete
    fun delete(recentSearchData: RecentSearchData)

    @Query("delete from recent_search_table")
    fun deleteAllRecentSearchData()

    @Query("select * from recent_search_table order by id desc")
    fun getAllRecentSearchData(): LiveData<List<RecentSearchData>>
}