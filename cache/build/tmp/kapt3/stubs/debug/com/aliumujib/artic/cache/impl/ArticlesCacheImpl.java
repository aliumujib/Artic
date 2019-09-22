package com.aliumujib.artic.cache.impl;

import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper;
import com.aliumujib.artic.cache.room.ArticlesDao;
import com.aliumujib.artic.data.model.ArticleEntity;
import com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eH\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/aliumujib/artic/cache/impl/ArticlesCacheImpl;", "Lcom/aliumujib/artic/data/repositories/contracts/cache/IArticlesCache;", "articlesDao", "Lcom/aliumujib/artic/cache/room/ArticlesDao;", "articleCacheModelMapper", "Lcom/aliumujib/artic/cache/models/mappers/ArticleCacheModelMapper;", "cacheTimeManager", "Lcom/aliumujib/artic/cache/impl/CacheTimeManager;", "(Lcom/aliumujib/artic/cache/room/ArticlesDao;Lcom/aliumujib/artic/cache/models/mappers/ArticleCacheModelMapper;Lcom/aliumujib/artic/cache/impl/CacheTimeManager;)V", "areArticlesCached", "", "clearArticles", "Lio/reactivex/Completable;", "getArticles", "Lio/reactivex/Flowable;", "", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "getBookmarkedArticles", "isArticlesCacheExpired", "saveArticles", "", "articles", "setArticleAsBookmarked", "article", "setArticleAsNotBookmarked", "articleId", "", "setLastCacheTime", "lastCache", "", "cache_debug"})
public final class ArticlesCacheImpl implements com.aliumujib.artic.data.repositories.contracts.cache.IArticlesCache {
    private final com.aliumujib.artic.cache.room.ArticlesDao articlesDao = null;
    private final com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper articleCacheModelMapper = null;
    private final com.aliumujib.artic.cache.impl.CacheTimeManager cacheTimeManager = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable clearArticles() {
        return null;
    }
    
    @java.lang.Override()
    public void saveArticles(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aliumujib.artic.data.model.ArticleEntity> articles) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Flowable<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> getArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Flowable<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> getBookmarkedArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable setArticleAsBookmarked(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.data.model.ArticleEntity article) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable setArticleAsNotBookmarked(int articleId) {
        return null;
    }
    
    @java.lang.Override()
    public boolean areArticlesCached() {
        return false;
    }
    
    @java.lang.Override()
    public void setLastCacheTime(long lastCache) {
    }
    
    @java.lang.Override()
    public boolean isArticlesCacheExpired() {
        return false;
    }
    
    @javax.inject.Inject()
    public ArticlesCacheImpl(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.room.ArticlesDao articlesDao, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper articleCacheModelMapper, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.impl.CacheTimeManager cacheTimeManager) {
        super();
    }
}