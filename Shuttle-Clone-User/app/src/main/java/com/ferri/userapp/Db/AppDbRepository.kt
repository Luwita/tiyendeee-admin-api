package com.ferri.userapp.Db

import android.app.Application
import androidx.lifecycle.LiveData
import com.ferri.userapp.utils.subscribeOnBackground

class AppDbRepository(application: Application) {

    private var recentSearchDao: RecentSearchDao
    private var allRecords: LiveData<List<RecentSearchData>>

    private val database = AppDatabase.getInstance(application)

    init {
        recentSearchDao = database.recentSearchDao()
        allRecords = recentSearchDao.getAllRecentSearchData()
    }

    fun insert(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.insert(recentSearchData)
        }
    }

    fun update(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.update(recentSearchData)
        }
    }

    fun delete(recentSearchData: RecentSearchData) {
        subscribeOnBackground {
            recentSearchDao.delete(recentSearchData)
        }
    }

    fun deleteAllRecentSearchData() {
        subscribeOnBackground {
            recentSearchDao.deleteAllRecentSearchData()
        }
    }

    fun getAllRecentSearchData(): LiveData<List<RecentSearchData>> {
        return allRecords
    }



}