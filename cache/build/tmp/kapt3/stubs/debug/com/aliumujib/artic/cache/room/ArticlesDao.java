package com.aliumujib.artic.cache.room;

import androidx.room.*;
import com.aliumujib.artic.cache.models.ArticleCacheModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\b\u0010\f\u001a\u00020\u0006H\'J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\nH\'J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0017\u00a8\u0006\u0011"}, d2 = {"Lcom/aliumujib/artic/cache/room/ArticlesDao;", "", "deleteAllArticles", "", "deleteArticle", "id", "", "getAllBookmarkedArticles", "Lio/reactivex/Flowable;", "", "Lcom/aliumujib/artic/cache/models/ArticleCacheModel;", "getAllCachedArticles", "getAllCachedArticlesCount", "getArticle", "insert", "app", "unBookmarkArticle", "cache_debug"})
public abstract interface ArticlesDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel> app);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.ArticleCacheModel app);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES WHERE id =:id")
    public abstract com.aliumujib.artic.cache.models.ArticleCacheModel getArticle(int id);
    
    @androidx.room.Transaction()
    public abstract void unBookmarkArticle(int id);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM ARTICLES ORDER BY date DESC ")
    public abstract int getAllCachedArticlesCount();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES ORDER BY date DESC ")
    public abstract io.reactivex.Flowable<java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel>> getAllCachedArticles();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES WHERE isBookmarked =  1 ORDER BY date DESC ")
    public abstract io.reactivex.Flowable<java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel>> getAllBookmarkedArticles();
    
    @androidx.room.Query(value = "DELETE FROM ARTICLES WHERE id =:id")
    public abstract void deleteArticle(int id);
    
    @androidx.room.Query(value = "DELETE FROM ARTICLES")
    public abstract void deleteAllArticles();
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        @androidx.room.Transaction()
        public static void unBookmarkArticle(com.aliumujib.artic.cache.room.ArticlesDao $this, int id) {
        }
    }
}