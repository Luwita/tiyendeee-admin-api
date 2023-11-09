package com.ferri.userapp.Db;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecentSearchDao_Impl implements RecentSearchDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RecentSearchData> __insertionAdapterOfRecentSearchData;

  private final EntityDeletionOrUpdateAdapter<RecentSearchData> __deletionAdapterOfRecentSearchData;

  private final EntityDeletionOrUpdateAdapter<RecentSearchData> __updateAdapterOfRecentSearchData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRecentSearchData;

  public RecentSearchDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecentSearchData = new EntityInsertionAdapter<RecentSearchData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `recent_search_table` (`pickUpLat`,`pickUpLng`,`dropLat`,`dropLng`,`pickUpAddress`,`dropAddress`,`date`,`id`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecentSearchData value) {
        if (value.getPickUpLat() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPickUpLat());
        }
        if (value.getPickUpLng() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPickUpLng());
        }
        if (value.getDropLat() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDropLat());
        }
        if (value.getDropLng() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDropLng());
        }
        if (value.getPickUpAddress() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPickUpAddress());
        }
        if (value.getDropAddress() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDropAddress());
        }
        if (value.getDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDate());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
      }
    };
    this.__deletionAdapterOfRecentSearchData = new EntityDeletionOrUpdateAdapter<RecentSearchData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `recent_search_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecentSearchData value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfRecentSearchData = new EntityDeletionOrUpdateAdapter<RecentSearchData>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `recent_search_table` SET `pickUpLat` = ?,`pickUpLng` = ?,`dropLat` = ?,`dropLng` = ?,`pickUpAddress` = ?,`dropAddress` = ?,`date` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecentSearchData value) {
        if (value.getPickUpLat() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPickUpLat());
        }
        if (value.getPickUpLng() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPickUpLng());
        }
        if (value.getDropLat() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDropLat());
        }
        if (value.getDropLng() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDropLng());
        }
        if (value.getPickUpAddress() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPickUpAddress());
        }
        if (value.getDropAddress() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDropAddress());
        }
        if (value.getDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDate());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAllRecentSearchData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from recent_search_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final RecentSearchData recentSearchData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecentSearchData.insert(recentSearchData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final RecentSearchData recentSearchData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRecentSearchData.handle(recentSearchData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final RecentSearchData recentSearchData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRecentSearchData.handle(recentSearchData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllRecentSearchData() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRecentSearchData.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllRecentSearchData.release(_stmt);
    }
  }

  @Override
  public LiveData<List<RecentSearchData>> getAllRecentSearchData() {
    final String _sql = "select * from recent_search_table order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"recent_search_table"}, false, new Callable<List<RecentSearchData>>() {
      @Override
      public List<RecentSearchData> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPickUpLat = CursorUtil.getColumnIndexOrThrow(_cursor, "pickUpLat");
          final int _cursorIndexOfPickUpLng = CursorUtil.getColumnIndexOrThrow(_cursor, "pickUpLng");
          final int _cursorIndexOfDropLat = CursorUtil.getColumnIndexOrThrow(_cursor, "dropLat");
          final int _cursorIndexOfDropLng = CursorUtil.getColumnIndexOrThrow(_cursor, "dropLng");
          final int _cursorIndexOfPickUpAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "pickUpAddress");
          final int _cursorIndexOfDropAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "dropAddress");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<RecentSearchData> _result = new ArrayList<RecentSearchData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final RecentSearchData _item;
            final String _tmpPickUpLat;
            if (_cursor.isNull(_cursorIndexOfPickUpLat)) {
              _tmpPickUpLat = null;
            } else {
              _tmpPickUpLat = _cursor.getString(_cursorIndexOfPickUpLat);
            }
            final String _tmpPickUpLng;
            if (_cursor.isNull(_cursorIndexOfPickUpLng)) {
              _tmpPickUpLng = null;
            } else {
              _tmpPickUpLng = _cursor.getString(_cursorIndexOfPickUpLng);
            }
            final String _tmpDropLat;
            if (_cursor.isNull(_cursorIndexOfDropLat)) {
              _tmpDropLat = null;
            } else {
              _tmpDropLat = _cursor.getString(_cursorIndexOfDropLat);
            }
            final String _tmpDropLng;
            if (_cursor.isNull(_cursorIndexOfDropLng)) {
              _tmpDropLng = null;
            } else {
              _tmpDropLng = _cursor.getString(_cursorIndexOfDropLng);
            }
            final String _tmpPickUpAddress;
            if (_cursor.isNull(_cursorIndexOfPickUpAddress)) {
              _tmpPickUpAddress = null;
            } else {
              _tmpPickUpAddress = _cursor.getString(_cursorIndexOfPickUpAddress);
            }
            final String _tmpDropAddress;
            if (_cursor.isNull(_cursorIndexOfDropAddress)) {
              _tmpDropAddress = null;
            } else {
              _tmpDropAddress = _cursor.getString(_cursorIndexOfDropAddress);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _item = new RecentSearchData(_tmpPickUpLat,_tmpPickUpLng,_tmpDropLat,_tmpDropLng,_tmpPickUpAddress,_tmpDropAddress,_tmpDate,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
