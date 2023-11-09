package com.ferri.userapp.Db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RecentSearchViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = AppDbRepository(app)
    private val allRecentSearchData = repository.getAllRecentSearchData()

    fun insert(recentSearchData: RecentSearchData) {
        repository.insert(recentSearchData)
    }

    fun update(recentSearchData: RecentSearchData) {
        repository.update(recentSearchData)
    }

    fun delete(RecentSearchData: RecentSearchData) {
        repository.delete(RecentSearchData)
    }

    fun deleteAllRecentSearchData() {
        repository.deleteAllRecentSearchData()
    }

    fun getAllRecentSearchData(): LiveData<List<RecentSearchData>> {
        return allRecentSearchData
    }

}