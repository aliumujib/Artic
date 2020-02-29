package com.aliumujib.artic.cache.room;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0018\u0010\t\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0007J\u001a\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0018\u0010\u000e\u001a\u00020\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nH\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/aliumujib/artic/cache/room/Converters;", "", "()V", "fromAuthorCacheModelEntity", "", "data", "Lcom/aliumujib/artic/cache/models/AuthorCacheModel;", "fromAuthorCacheModelString", "value", "fromCategoryCacheModelList", "", "Lcom/aliumujib/artic/cache/models/CategoryCacheModel;", "fromCategoryCacheModelString", "fromListString", "fromStringArrayList", "list", "cache_debug"})
public final class Converters {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final java.util.List<java.lang.Object> fromListString(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.lang.String fromStringArrayList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends java.lang.Object> list) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final java.util.List<com.aliumujib.artic.cache.models.CategoryCacheModel> fromCategoryCacheModelString(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.lang.String fromCategoryCacheModelList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.aliumujib.artic.cache.models.CategoryCacheModel> data) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final com.aliumujib.artic.cache.models.AuthorCacheModel fromAuthorCacheModelString(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.TypeConverter()
    public final java.lang.String fromAuthorCacheModelEntity(@org.jetbrains.annotations.Nullable()
    com.aliumujib.artic.cache.models.AuthorCacheModel data) {
        return null;
    }
    
    public Converters() {
        super();
    }
}