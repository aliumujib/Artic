package com.aliumujib.artic.cache.room;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0019\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\'J\b\u0010\r\u001a\u00020\u0006H\'J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u001f\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/aliumujib/artic/cache/room/ArticlesDao;", "", "deleteAllArticles", "", "deleteArticle", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBookmarkedArticles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/aliumujib/artic/cache/models/ArticleCacheModel;", "getAllCachedArticles", "getAllCachedArticlesCount", "getArticle", "insert", "app", "(Lcom/aliumujib/artic/cache/models/ArticleCacheModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unBookmarkArticle", "cache_debug"})
public abstract interface ArticlesDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel> app, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.ArticleCacheModel app, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES WHERE id =:id")
    public abstract com.aliumujib.artic.cache.models.ArticleCacheModel getArticle(int id);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Transaction()
    public abstract java.lang.Object unBookmarkArticle(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM ARTICLES ORDER BY date DESC ")
    public abstract int getAllCachedArticlesCount();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES ORDER BY date DESC ")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel>> getAllCachedArticles();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ARTICLES WHERE isBookmarked =  1 ORDER BY date DESC ")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel>> getAllBookmarkedArticles();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM ARTICLES WHERE id =:id")
    public abstract java.lang.Object deleteArticle(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @androidx.room.Query(value = "DELETE FROM ARTICLES")
    public abstract void deleteAllArticles();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable()
        @androidx.room.Transaction()
        public static java.lang.Object unBookmarkArticle(com.aliumujib.artic.cache.room.ArticlesDao $this, int id, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
            return null;
        }
    }
}