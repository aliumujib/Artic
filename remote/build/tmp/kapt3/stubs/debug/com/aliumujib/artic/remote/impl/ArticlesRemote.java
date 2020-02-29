package com.aliumujib.artic.remote.impl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J$\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/aliumujib/artic/remote/impl/ArticlesRemote;", "Lcom/aliumujib/artic/data/repositories/contracts/remote/IArticlesRemote;", "wordPressAPI", "Lcom/aliumujib/artic/remote/api/WordPressAPI;", "postsMapper", "Lcom/aliumujib/artic/remote/mapper/PostsMapper;", "(Lcom/aliumujib/artic/remote/api/WordPressAPI;Lcom/aliumujib/artic/remote/mapper/PostsMapper;)V", "getPostsMapper", "()Lcom/aliumujib/artic/remote/mapper/PostsMapper;", "setPostsMapper", "(Lcom/aliumujib/artic/remote/mapper/PostsMapper;)V", "getWordPressAPI", "()Lcom/aliumujib/artic/remote/api/WordPressAPI;", "setWordPressAPI", "(Lcom/aliumujib/artic/remote/api/WordPressAPI;)V", "getArticles", "Lio/reactivex/Observable;", "", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "page", "", "searchArticles", "search", "", "Companion", "remote_debug"})
public final class ArticlesRemote implements com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote {
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.api.WordPressAPI wordPressAPI;
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.mapper.PostsMapper postsMapper;
    private static final int PER_PAGE = 15;
    public static final com.aliumujib.artic.remote.impl.ArticlesRemote.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> getArticles(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.util.List<com.aliumujib.artic.data.model.ArticleEntity>> searchArticles(@org.jetbrains.annotations.NotNull()
    java.lang.String search, int page) {
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/aliumujib/artic/remote/impl/ArticlesRemote$Companion;", "", "()V", "PER_PAGE", "", "getPER_PAGE", "()I", "remote_debug"})
    public static final class Companion {
        
        public final int getPER_PAGE() {
            return 0;
        }
        
        private Companion() {
            super();
        }
    }
}