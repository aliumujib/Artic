package com.aliumujib.artic.cache.impl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016J\u0011\u0010\u0013\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/aliumujib/artic/cache/impl/ArticlesCacheImpl;", "Lcom/aliumujib/artic/data/repositories/contracts/cache/IArticlesCache;", "articlesDao", "Lcom/aliumujib/artic/cache/room/ArticlesDao;", "articleCacheModelMapper", "Lcom/aliumujib/artic/cache/models/mappers/ArticleCacheModelMapper;", "cacheTimeManager", "Lcom/aliumujib/artic/cache/impl/CacheTimeManager;", "(Lcom/aliumujib/artic/cache/room/ArticlesDao;Lcom/aliumujib/artic/cache/models/mappers/ArticleCacheModelMapper;Lcom/aliumujib/artic/cache/impl/CacheTimeManager;)V", "areArticlesCached", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearArticles", "", "getArticles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "getBookmarkedArticles", "isArticlesCacheExpired", "saveArticles", "articles", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setArticleAsBookmarked", "article", "(Lcom/aliumujib/artic/data/model/ArticleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setArticleAsNotBookmarked", "articleId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLastCacheTime", "lastCache", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cache_debug"})
public final class ArticlesCacheImpl implements com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache {
    private final com.aliumujib.artic.cache.room.ArticlesDao articlesDao = null;
    private final com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper articleCacheModelMapper = null;
    private final com.aliumujib.artic.cache.impl.CacheTimeManager cacheTimeManager = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object clearArticles(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveArticles(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aliumujib.artic.data.model.ArticleEntity> articles, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> getArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> getBookmarkedArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object setArticleAsBookmarked(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.data.model.ArticleEntity article, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object setArticleAsNotBookmarked(int articleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object areArticlesCached(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object setLastCacheTime(long lastCache, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isArticlesCacheExpired(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p0) {
        return null;
    }
    
    @javax.inject.Inject()
    public ArticlesCacheImpl(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.room.ArticlesDao articlesDao, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper articleCacheModelMapper, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.impl.CacheTimeManager cacheTimeManager) {
        super();
    }
}