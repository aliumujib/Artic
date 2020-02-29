package com.aliumujib.artic.cache.models.mappers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH\u0016J\u0015\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bH\u0016J\u0017\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0017H\u0016\u00a8\u0006\u001a"}, d2 = {"Lcom/aliumujib/artic/cache/models/mappers/CacheModelMapper;", "M", "E", "", "mapToEntity", "model", "(Ljava/lang/Object;)Ljava/lang/Object;", "mapToEntityList", "", "models", "mapToModel", "entity", "mapToModelList", "safeBoolean", "", "boolean", "(Ljava/lang/Boolean;)Z", "safeList", "safeParse", "Ljava/util/Date;", "format", "Ljava/text/SimpleDateFormat;", "from", "", "safeString", "string", "cache_debug"})
public abstract interface CacheModelMapper<M extends java.lang.Object, E extends java.lang.Object> {
    
    public abstract E mapToEntity(M model);
    
    public abstract M mapToModel(E entity);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<E> mapToEntityList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends M> models);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<M> mapToModelList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends E> models);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<java.lang.Object> safeList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends java.lang.Object> models);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String safeString(@org.jetbrains.annotations.Nullable()
    java.lang.String string);
    
    public abstract boolean safeBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0_32355860);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Date safeParse(@org.jetbrains.annotations.NotNull()
    java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
    java.lang.String from);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.List<E> mapToEntityList(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends M> models) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.List<M> mapToModelList(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends E> models) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.List<java.lang.Object> safeList(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends java.lang.Object> models) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.lang.String safeString(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.Nullable()
        java.lang.String string) {
            return null;
        }
        
        public static <M extends java.lang.Object, E extends java.lang.Object>boolean safeBoolean(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean p1_32355860) {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.Date safeParse(com.aliumujib.artic.cache.models.mappers.CacheModelMapper<M, E> $this, @org.jetbrains.annotations.NotNull()
        java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
        java.lang.String from) {
            return null;
        }
    }
}