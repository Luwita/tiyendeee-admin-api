package com.ferri.userapp.Db;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\f"}, d2 = {"Lcom/ferri/userapp/Db/RecentSearchDao;", "", "delete", "", "recentSearchData", "Lcom/ferri/userapp/Db/RecentSearchData;", "deleteAllRecentSearchData", "getAllRecentSearchData", "Landroidx/lifecycle/LiveData;", "", "insert", "update", "app_debug"})
@androidx.room.Dao
public abstract interface RecentSearchDao {
    
    @androidx.room.Insert
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData);
    
    @androidx.room.Query(value = "delete from recent_search_table")
    public abstract void deleteAllRecentSearchData();
    
    @androidx.room.Query(value = "select * from recent_search_table order by id desc")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ferri.userapp.Db.RecentSearchData>> getAllRecentSearchData();
}