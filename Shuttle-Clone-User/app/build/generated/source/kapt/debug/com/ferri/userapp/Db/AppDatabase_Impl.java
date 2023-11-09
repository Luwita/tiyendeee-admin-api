package com.ferri.userapp.Db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RecentSearchDao _recentSearchDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `recent_search_table` (`pickUpLat` TEXT NOT NULL, `pickUpLng` TEXT NOT NULL, `dropLat` TEXT NOT NULL, `dropLng` TEXT NOT NULL, `pickUpAddress` TEXT NOT NULL, `dropAddress` TEXT NOT NULL, `date` TEXT NOT NULL, `id` INTEGER, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '20af1f557bfcbf7ae72e68b797cda042')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `recent_search_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsRecentSearchTable = new HashMap<String, TableInfo.Column>(8);
        _columnsRecentSearchTable.put("pickUpLat", new TableInfo.Column("pickUpLat", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("pickUpLng", new TableInfo.Column("pickUpLng", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("dropLat", new TableInfo.Column("dropLat", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("dropLng", new TableInfo.Column("dropLng", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("pickUpAddress", new TableInfo.Column("pickUpAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("dropAddress", new TableInfo.Column("dropAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecentSearchTable.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecentSearchTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRecentSearchTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRecentSearchTable = new TableInfo("recent_search_table", _columnsRecentSearchTable, _foreignKeysRecentSearchTable, _indicesRecentSearchTable);
        final TableInfo _existingRecentSearchTable = TableInfo.read(_db, "recent_search_table");
        if (! _infoRecentSearchTable.equals(_existingRecentSearchTable)) {
          return new RoomOpenHelper.ValidationResult(false, "recent_search_table(com.ferri.userapp.Db.RecentSearchData).\n"
                  + " Expected:\n" + _infoRecentSearchTable + "\n"
                  + " Found:\n" + _existingRecentSearchTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "20af1f557bfcbf7ae72e68b797cda042", "a568deada9266b6a3062e7351e271a23");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "recent_search_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `recent_search_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RecentSearchDao.class, RecentSearchDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public RecentSearchDao recentSearchDao() {
    if (_recentSearchDao != null) {
      return _recentSearchDao;
    } else {
      synchronized(this) {
        if(_recentSearchDao == null) {
          _recentSearchDao = new RecentSearchDao_Impl(this);
        }
        return _recentSearchDao;
      }
    }
  }
}
