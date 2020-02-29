package com.aliumujib.artic.cache.models.mappers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/aliumujib/artic/cache/models/mappers/ArticleCacheModelMapper;", "Lcom/aliumujib/artic/cache/models/mappers/CacheModelMapper;", "Lcom/aliumujib/artic/cache/models/ArticleCacheModel;", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "authorModelMapper", "Lcom/aliumujib/artic/cache/models/mappers/AuthorModelMapper;", "categoriesModelMapper", "Lcom/aliumujib/artic/cache/models/mappers/CategoriesModelMapper;", "(Lcom/aliumujib/artic/cache/models/mappers/AuthorModelMapper;Lcom/aliumujib/artic/cache/models/mappers/CategoriesModelMapper;)V", "mapToEntity", "model", "mapToModel", "entity", "cache_debug"})
public final class ArticleCacheModelMapper implements com.aliumujib.artic.cache.models.mappers.CacheModelMapper<com.aliumujib.artic.cache.models.ArticleCacheModel, com.aliumujib.artic.data.model.ArticleEntity> {
    private final com.aliumujib.artic.cache.models.mappers.AuthorModelMapper authorModelMapper = null;
    private final com.aliumujib.artic.cache.models.mappers.CategoriesModelMapper categoriesModelMapper = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.aliumujib.artic.data.model.ArticleEntity mapToEntity(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.ArticleCacheModel model) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.aliumujib.artic.cache.models.ArticleCacheModel mapToModel(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.data.model.ArticleEntity entity) {
        return null;
    }
    
    @javax.inject.Inject()
    public ArticleCacheModelMapper(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.mappers.AuthorModelMapper authorModelMapper, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.cache.models.mappers.CategoriesModelMapper categoriesModelMapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.aliumujib.artic.data.model.ArticleEntity> mapToEntityList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel> models) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.aliumujib.artic.cache.models.ArticleCacheModel> mapToModelList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.aliumujib.artic.data.model.ArticleEntity> models) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<java.lang.Object> safeList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends java.lang.Object> models) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String safeString(@org.jetbrains.annotations.Nullable()
    java.lang.String string) {
        return null;
    }
    
    public boolean safeBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0_32355860) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.Date safeParse(@org.jetbrains.annotations.NotNull()
    java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
    java.lang.String from) {
        return null;
    }
}