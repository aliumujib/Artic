package com.aliumujib.artic.remote.impl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/aliumujib/artic/remote/impl/ArticlesRemote;", "Lcom/aliumujib/artic/data/repositories/contracts/remote/IArticlesRemote;", "wordPressAPI", "Lcom/aliumujib/artic/remote/api/WordPressAPI;", "postsMapper", "Lcom/aliumujib/artic/remote/mapper/PostsMapper;", "(Lcom/aliumujib/artic/remote/api/WordPressAPI;Lcom/aliumujib/artic/remote/mapper/PostsMapper;)V", "getPostsMapper", "()Lcom/aliumujib/artic/remote/mapper/PostsMapper;", "setPostsMapper", "(Lcom/aliumujib/artic/remote/mapper/PostsMapper;)V", "getWordPressAPI", "()Lcom/aliumujib/artic/remote/api/WordPressAPI;", "setWordPressAPI", "(Lcom/aliumujib/artic/remote/api/WordPressAPI;)V", "getArticles", "", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchArticles", "search", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "remote_debug"})
public final class ArticlesRemote implements com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote {
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.api.WordPressAPI wordPressAPI;
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.mapper.PostsMapper postsMapper;
    private static final int PER_PAGE = 15;
    public static final com.aliumujib.artic.remote.impl.ArticlesRemote.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getArticles(int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object searchArticles(@org.jetbrains.annotations.NotNull()
    java.lang.String search, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aliumujib.artic.remote.api.WordPressAPI getWordPressAPI() {
        return null;
    }
    
    public final void setWordPressAPI(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.api.WordPressAPI p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aliumujib.artic.remote.mapper.PostsMapper getPostsMapper() {
        return null;
    }
    
    public final void setPostsMapper(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.PostsMapper p0) {
    }
    
    @javax.inject.Inject()
    public ArticlesRemote(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.api.WordPressAPI wordPressAPI, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.PostsMapper postsMapper) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/aliumujib/artic/remote/impl/ArticlesRemote$Companion;", "", "()V", "PER_PAGE", "", "remote_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}