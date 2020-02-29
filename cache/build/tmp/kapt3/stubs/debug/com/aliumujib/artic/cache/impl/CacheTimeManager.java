package com.aliumujib.artic.cache.impl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/aliumujib/artic/cache/impl/CacheTimeManager;", "Lcom/aliumujib/artic/cache/utils/CoreSharedPrefManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "getLastCacheTime", "", "saveLastCacheTime", "", "time", "Companion", "cache_debug"})
public final class CacheTimeManager extends com.aliumujib.artic.cache.utils.CoreSharedPrefManager {
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    private static final java.lang.String APP_NAME = "ARTIC";
    private static final java.lang.String LAST_CACHE_TIME = "LAST_CACHE_TIME";
    public static final com.aliumujib.artic.cache.impl.CacheTimeManager.Companion Companion = null;
    
    public final void saveLastCacheTime(long time) {
    }
    
    public final long getLastCacheTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @javax.inject.Inject()
    public CacheTimeManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/aliumujib/artic/cache/impl/CacheTimeManager$Companion;", "", "()V", "APP_NAME", "", "LAST_CACHE_TIME", "cache_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}