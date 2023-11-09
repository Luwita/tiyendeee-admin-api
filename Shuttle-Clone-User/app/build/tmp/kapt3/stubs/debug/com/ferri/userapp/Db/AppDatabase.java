package com.ferri.userapp.Db;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/ferri/userapp/Db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "recentSearchDao", "Lcom/ferri/userapp/Db/RecentSearchDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.ferri.userapp.Db.RecentSearchData.class}, version = 1)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.Nullable
    private static com.ferri.userapp.Db.AppDatabase instance;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.RoomDatabase.Callback roomCallback = null;
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.Db.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.ferri.userapp.Db.RecentSearchDao recentSearchDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ferri/userapp/Db/AppDatabase$Companion;", "", "()V", "instance", "Lcom/ferri/userapp/Db/AppDatabase;", "roomCallback", "Landroidx/room/RoomDatabase$Callback;", "getInstance", "ctx", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.Synchronized
        @org.jetbrains.annotations.NotNull
        public final synchronized com.ferri.userapp.Db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context ctx) {
            return null;
        }
    }
}