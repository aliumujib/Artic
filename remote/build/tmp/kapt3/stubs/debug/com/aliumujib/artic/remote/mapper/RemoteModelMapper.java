package com.aliumujib.artic.remote.mapper;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0016\u00a8\u0006\u0012"}, d2 = {"Lcom/aliumujib/artic/remote/mapper/RemoteModelMapper;", "M", "E", "", "mapFromModel", "model", "(Ljava/lang/Object;)Ljava/lang/Object;", "mapModelList", "", "models", "safeParse", "Ljava/util/Date;", "format", "Ljava/text/SimpleDateFormat;", "from", "", "safeString", "string", "remote_debug"})
public abstract interface RemoteModelMapper<M extends java.lang.Object, E extends java.lang.Object> {
    
    public abstract E mapFromModel(M model);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<E> mapModelList(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends M> models);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String safeString(@org.jetbrains.annotations.Nullable()
    java.lang.String string);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Date safeParse(@org.jetbrains.annotations.NotNull()
    java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
    java.lang.String from);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.List<E> mapModelList(com.aliumujib.artic.remote.mapper.RemoteModelMapper<? super M, ? extends E> $this, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends M> models) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.lang.String safeString(com.aliumujib.artic.remote.mapper.RemoteModelMapper<? super M, ? extends E> $this, @org.jetbrains.annotations.Nullable()
        java.lang.String string) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static <M extends java.lang.Object, E extends java.lang.Object>java.util.Date safeParse(com.aliumujib.artic.remote.mapper.RemoteModelMapper<? super M, ? extends E> $this, @org.jetbrains.annotations.NotNull()
        java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
        java.lang.String from) {
            return null;
        }
    }
}