package com.ferri.userapp.Db;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\u000eJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ferri/userapp/Db/AppDbRepository;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allRecords", "Landroidx/lifecycle/LiveData;", "", "Lcom/ferri/userapp/Db/RecentSearchData;", "database", "Lcom/ferri/userapp/Db/AppDatabase;", "recentSearchDao", "Lcom/ferri/userapp/Db/RecentSearchDao;", "delete", "", "recentSearchData", "deleteAllRecentSearchData", "getAllRecentSearchData", "insert", "update", "app_debug"})
public final class AppDbRepository {
    @org.jetbrains.annotations.NotNull
    private com.ferri.userapp.Db.RecentSearchDao recentSearchDao;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.LiveData<java.util.List<com.ferri.userapp.Db.RecentSearchData>> allRecords;
    @org.jetbrains.annotations.NotNull
    private final com.ferri.userapp.Db.AppDatabase database = null;
    
    public AppDbRepository(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super();
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Db.RecentSearchData recentSearchData) {
    }
    
    public final void deleteAllRecentSearchData() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.ferri.userapp.Db.RecentSearchData>> getAllRecentSearchData() {
        return null;
    }
}