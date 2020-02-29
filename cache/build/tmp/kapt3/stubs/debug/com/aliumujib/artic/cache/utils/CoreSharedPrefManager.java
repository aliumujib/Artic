package com.aliumujib.artic.cache.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0004J\u001b\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0004\u00a2\u0006\u0002\u0010\u0012J\'\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u0001H\u0011H\u0004\u00a2\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/aliumujib/artic/cache/utils/CoreSharedPrefManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "sharedPreferencesEditor", "Landroid/content/SharedPreferences$Editor;", "getSharedPreferencesEditor", "()Landroid/content/SharedPreferences$Editor;", "clearAll", "", "delete", "key", "", "getPref", "T", "(Ljava/lang/String;)Ljava/lang/Object;", "defValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "savePref", "value", "Companion", "cache_debug"})
public class CoreSharedPrefManager {
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences.Editor sharedPreferencesEditor = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APP_NAME = "EYOWO";
    public static final com.aliumujib.artic.cache.utils.CoreSharedPrefManager.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences.Editor getSharedPreferencesEditor() {
        return null;
    }
    
    protected final void delete(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    protected final void savePref(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.Object value) {
    }
    
    protected final <T extends java.lang.Object>T getPref(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final <T extends java.lang.Object>T getPref(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    T defValue) {
        return null;
    }
    
    public final void clearAll() {
    }
    
    public CoreSharedPrefManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/aliumujib/artic/cache/utils/CoreSharedPrefManager$Companion;", "", "()V", "APP_NAME", "", "cache_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}