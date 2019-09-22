package com.aliumujib.artic.cache.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DBClass_Impl extends DBClass {
  private volatile ArticlesDao _articlesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ARTICLES` (`id` INTEGER NOT NULL, `type` TEXT NOT NULL, `slug` TEXT NOT NULL, `url` TEXT NOT NULL, `status` TEXT NOT NULL, `title` TEXT NOT NULL, `title_plain` TEXT NOT NULL, `content` TEXT NOT NULL, `excerpt` TEXT NOT NULL, `date` INTEGER NOT NULL, `modified` TEXT NOT NULL, `thumbnail` TEXT NOT NULL, `imageURL` TEXT NOT NULL, `comment_count` INTEGER NOT NULL, `categories` TEXT NOT NULL, `author` TEXT NOT NULL, `isBookmarked` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e568abf52554ba2e353f851b40a35764')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ARTICLES`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
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
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsARTICLES = new HashMap<String, TableInfo.Column>(17);
        _columnsARTICLES.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsARTICLES.put("type", new TableInfo.Column("type", "TEXT", true, 0));
        _columnsARTICLES.put("slug", new TableInfo.Column("slug", "TEXT", true, 0));
        _columnsARTICLES.put("url", new TableInfo.Column("url", "TEXT", true, 0));
        _columnsARTICLES.put("status", new TableInfo.Column("status", "TEXT", true, 0));
        _columnsARTICLES.put("title", new TableInfo.Column("title", "TEXT", true, 0));
        _columnsARTICLES.put("title_plain", new TableInfo.Column("title_plain", "TEXT", true, 0));
        _columnsARTICLES.put("content", new TableInfo.Column("content", "TEXT", true, 0));
        _columnsARTICLES.put("excerpt", new TableInfo.Column("excerpt", "TEXT", true, 0));
        _columnsARTICLES.put("date", new TableInfo.Column("date", "INTEGER", true, 0));
        _columnsARTICLES.put("modified", new TableInfo.Column("modified", "TEXT", true, 0));
        _columnsARTICLES.put("thumbnail", new TableInfo.Column("thumbnail", "TEXT", true, 0));
        _columnsARTICLES.put("imageURL", new TableInfo.Column("imageURL", "TEXT", true, 0));
        _columnsARTICLES.put("comment_count", new TableInfo.Column("comment_count", "INTEGER", true, 0));
        _columnsARTICLES.put("categories", new TableInfo.Column("categories", "TEXT", true, 0));
        _columnsARTICLES.put("author", new TableInfo.Column("author", "TEXT", true, 0));
        _columnsARTICLES.put("isBookmarked", new TableInfo.Column("isBookmarked", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysARTICLES = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesARTICLES = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoARTICLES = new TableInfo("ARTICLES", _columnsARTICLES, _foreignKeysARTICLES, _indicesARTICLES);
        final TableInfo _existingARTICLES = TableInfo.read(_db, "ARTICLES");
        if (! _infoARTICLES.equals(_existingARTICLES)) {
          throw new IllegalStateException("Migration didn't properly handle ARTICLES(com.aliumujib.artic.cache.models.ArticleCacheModel).\n"
                  + " Expected:\n" + _infoARTICLES + "\n"
                  + " Found:\n" + _existingARTICLES);
        }
      }
    }, "e568abf52554ba2e353f851b40a35764", "63a5d595abe05a1c71aae9b9989adb36");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ARTICLES");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ARTICLES`");
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
  public ArticlesDao articlesDao() {
    if (_articlesDao != null) {
      return _articlesDao;
    } else {
      synchronized(this) {
        if(_articlesDao == null) {
          _articlesDao = new ArticlesDao_Impl(this);
        }
        return _articlesDao;
      }
    }
  }
}
