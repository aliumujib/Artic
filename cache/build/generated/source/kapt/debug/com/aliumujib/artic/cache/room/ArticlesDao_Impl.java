package com.aliumujib.artic.cache.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.aliumujib.artic.cache.models.ArticleCacheModel;
import com.aliumujib.artic.cache.models.AuthorCacheModel;
import com.aliumujib.artic.cache.models.CategoryCacheModel;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticlesDao_Impl implements ArticlesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleCacheModel> __insertionAdapterOfArticleCacheModel;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteArticle;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllArticles;

  public ArticlesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleCacheModel = new EntityInsertionAdapter<ArticleCacheModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ARTICLES` (`id`,`type`,`slug`,`url`,`status`,`title`,`title_plain`,`content`,`excerpt`,`date`,`modified`,`thumbnail`,`imageURL`,`comment_count`,`categories`,`author`,`isBookmarked`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleCacheModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getType());
        }
        if (value.getSlug() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSlug());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrl());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStatus());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getTitle_plain() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTitle_plain());
        }
        if (value.getContent() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContent());
        }
        if (value.getExcerpt() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getExcerpt());
        }
        stmt.bindLong(10, value.getDate());
        if (value.getModified() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getModified());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getThumbnail());
        }
        if (value.getImageURL() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getImageURL());
        }
        stmt.bindLong(14, value.getComment_count());
        final String _tmp;
        _tmp = __converters.fromCategoryCacheModelList(value.getCategories());
        if (_tmp == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __converters.fromAuthorCacheModelEntity(value.getAuthor());
        if (_tmp_1 == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, _tmp_1);
        }
        final int _tmp_2;
        _tmp_2 = value.isBookmarked() ? 1 : 0;
        stmt.bindLong(17, _tmp_2);
      }
    };
    this.__preparedStmtOfDeleteArticle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ARTICLES WHERE id =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllArticles = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ARTICLES";
        return _query;
      }
    };
  }

  @Override
  public void insert(final List<ArticleCacheModel> app) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleCacheModel.insert(app);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final ArticleCacheModel app) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleCacheModel.insert(app);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void unBookmarkArticle(final int id) {
    __db.beginTransaction();
    try {
      ArticlesDao.DefaultImpls.unBookmarkArticle(ArticlesDao_Impl.this, id);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteArticle(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteArticle.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteArticle.release(_stmt);
    }
  }

  @Override
  public void deleteAllArticles() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllArticles.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllArticles.release(_stmt);
    }
  }

  @Override
  public ArticleCacheModel getArticle(final int id) {
    final String _sql = "SELECT * FROM ARTICLES WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfTitlePlain = CursorUtil.getColumnIndexOrThrow(_cursor, "title_plain");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfExcerpt = CursorUtil.getColumnIndexOrThrow(_cursor, "excerpt");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfModified = CursorUtil.getColumnIndexOrThrow(_cursor, "modified");
      final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
      final int _cursorIndexOfImageURL = CursorUtil.getColumnIndexOrThrow(_cursor, "imageURL");
      final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
      final ArticleCacheModel _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        final String _tmpSlug;
        _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpTitle_plain;
        _tmpTitle_plain = _cursor.getString(_cursorIndexOfTitlePlain);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final String _tmpExcerpt;
        _tmpExcerpt = _cursor.getString(_cursorIndexOfExcerpt);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        final String _tmpModified;
        _tmpModified = _cursor.getString(_cursorIndexOfModified);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        final String _tmpImageURL;
        _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
        final int _tmpComment_count;
        _tmpComment_count = _cursor.getInt(_cursorIndexOfCommentCount);
        final List<CategoryCacheModel> _tmpCategories;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfCategories);
        _tmpCategories = __converters.fromCategoryCacheModelString(_tmp);
        final AuthorCacheModel _tmpAuthor;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfAuthor);
        _tmpAuthor = __converters.fromAuthorCacheModelString(_tmp_1);
        final boolean _tmpIsBookmarked;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsBookmarked);
        _tmpIsBookmarked = _tmp_2 != 0;
        _result = new ArticleCacheModel(_tmpId,_tmpType,_tmpSlug,_tmpUrl,_tmpStatus,_tmpTitle,_tmpTitle_plain,_tmpContent,_tmpExcerpt,_tmpDate,_tmpModified,_tmpThumbnail,_tmpImageURL,_tmpComment_count,_tmpCategories,_tmpAuthor,_tmpIsBookmarked);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAllCachedArticlesCount() {
    final String _sql = "SELECT COUNT(*) FROM ARTICLES ORDER BY date DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flowable<List<ArticleCacheModel>> getAllCachedArticles() {
    final String _sql = "SELECT * FROM ARTICLES ORDER BY date DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"ARTICLES"}, new Callable<List<ArticleCacheModel>>() {
      @Override
      public List<ArticleCacheModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTitlePlain = CursorUtil.getColumnIndexOrThrow(_cursor, "title_plain");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfExcerpt = CursorUtil.getColumnIndexOrThrow(_cursor, "excerpt");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfModified = CursorUtil.getColumnIndexOrThrow(_cursor, "modified");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfImageURL = CursorUtil.getColumnIndexOrThrow(_cursor, "imageURL");
          final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final List<ArticleCacheModel> _result = new ArrayList<ArticleCacheModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticleCacheModel _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpSlug;
            _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpTitle_plain;
            _tmpTitle_plain = _cursor.getString(_cursorIndexOfTitlePlain);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpExcerpt;
            _tmpExcerpt = _cursor.getString(_cursorIndexOfExcerpt);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpModified;
            _tmpModified = _cursor.getString(_cursorIndexOfModified);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpImageURL;
            _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
            final int _tmpComment_count;
            _tmpComment_count = _cursor.getInt(_cursorIndexOfCommentCount);
            final List<CategoryCacheModel> _tmpCategories;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategories);
            _tmpCategories = __converters.fromCategoryCacheModelString(_tmp);
            final AuthorCacheModel _tmpAuthor;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfAuthor);
            _tmpAuthor = __converters.fromAuthorCacheModelString(_tmp_1);
            final boolean _tmpIsBookmarked;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp_2 != 0;
            _item = new ArticleCacheModel(_tmpId,_tmpType,_tmpSlug,_tmpUrl,_tmpStatus,_tmpTitle,_tmpTitle_plain,_tmpContent,_tmpExcerpt,_tmpDate,_tmpModified,_tmpThumbnail,_tmpImageURL,_tmpComment_count,_tmpCategories,_tmpAuthor,_tmpIsBookmarked);
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

  @Override
  public Flowable<List<ArticleCacheModel>> getAllBookmarkedArticles() {
    final String _sql = "SELECT * FROM ARTICLES WHERE isBookmarked =  1 ORDER BY date DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"ARTICLES"}, new Callable<List<ArticleCacheModel>>() {
      @Override
      public List<ArticleCacheModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTitlePlain = CursorUtil.getColumnIndexOrThrow(_cursor, "title_plain");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfExcerpt = CursorUtil.getColumnIndexOrThrow(_cursor, "excerpt");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfModified = CursorUtil.getColumnIndexOrThrow(_cursor, "modified");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfImageURL = CursorUtil.getColumnIndexOrThrow(_cursor, "imageURL");
          final int _cursorIndexOfCommentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_count");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final List<ArticleCacheModel> _result = new ArrayList<ArticleCacheModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ArticleCacheModel _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpSlug;
            _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpTitle_plain;
            _tmpTitle_plain = _cursor.getString(_cursorIndexOfTitlePlain);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpExcerpt;
            _tmpExcerpt = _cursor.getString(_cursorIndexOfExcerpt);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpModified;
            _tmpModified = _cursor.getString(_cursorIndexOfModified);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpImageURL;
            _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
            final int _tmpComment_count;
            _tmpComment_count = _cursor.getInt(_cursorIndexOfCommentCount);
            final List<CategoryCacheModel> _tmpCategories;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategories);
            _tmpCategories = __converters.fromCategoryCacheModelString(_tmp);
            final AuthorCacheModel _tmpAuthor;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfAuthor);
            _tmpAuthor = __converters.fromAuthorCacheModelString(_tmp_1);
            final boolean _tmpIsBookmarked;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp_2 != 0;
            _item = new ArticleCacheModel(_tmpId,_tmpType,_tmpSlug,_tmpUrl,_tmpStatus,_tmpTitle,_tmpTitle_plain,_tmpContent,_tmpExcerpt,_tmpDate,_tmpModified,_tmpThumbnail,_tmpImageURL,_tmpComment_count,_tmpCategories,_tmpAuthor,_tmpIsBookmarked);
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
}
