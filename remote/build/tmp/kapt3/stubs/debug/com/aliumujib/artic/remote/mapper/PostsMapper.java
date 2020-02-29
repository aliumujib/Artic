package com.aliumujib.artic.remote.mapper;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/aliumujib/artic/remote/mapper/PostsMapper;", "Lcom/aliumujib/artic/remote/mapper/RemoteModelMapper;", "Lcom/aliumujib/artic/remote/models/Post;", "Lcom/aliumujib/artic/data/model/ArticleEntity;", "categoryMapper", "Lcom/aliumujib/artic/remote/mapper/CategoryMapper;", "authorMapper", "Lcom/aliumujib/artic/remote/mapper/AuthorMapper;", "(Lcom/aliumujib/artic/remote/mapper/CategoryMapper;Lcom/aliumujib/artic/remote/mapper/AuthorMapper;)V", "getAuthorMapper", "()Lcom/aliumujib/artic/remote/mapper/AuthorMapper;", "setAuthorMapper", "(Lcom/aliumujib/artic/remote/mapper/AuthorMapper;)V", "getCategoryMapper", "()Lcom/aliumujib/artic/remote/mapper/CategoryMapper;", "setCategoryMapper", "(Lcom/aliumujib/artic/remote/mapper/CategoryMapper;)V", "mapFromModel", "model", "remote_debug"})
public final class PostsMapper implements com.aliumujib.artic.remote.mapper.RemoteModelMapper<com.aliumujib.artic.remote.models.Post, com.aliumujib.artic.data.model.ArticleEntity> {
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.mapper.CategoryMapper categoryMapper;
    @org.jetbrains.annotations.NotNull()
    private com.aliumujib.artic.remote.mapper.AuthorMapper authorMapper;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.aliumujib.artic.data.model.ArticleEntity mapFromModel(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.models.Post model) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aliumujib.artic.remote.mapper.CategoryMapper getCategoryMapper() {
        return null;
    }
    
    public final void setCategoryMapper(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.CategoryMapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aliumujib.artic.remote.mapper.AuthorMapper getAuthorMapper() {
        return null;
    }
    
    public final void setAuthorMapper(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.AuthorMapper p0) {
    }
    
    @javax.inject.Inject()
    public PostsMapper(@org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.CategoryMapper categoryMapper, @org.jetbrains.annotations.NotNull()
    com.aliumujib.artic.remote.mapper.AuthorMapper authorMapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.aliumujib.artic.data.model.ArticleEntity> mapModelList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.aliumujib.artic.remote.models.Post> models) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String safeString(@org.jetbrains.annotations.Nullable()
    java.lang.String string) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.Date safeParse(@org.jetbrains.annotations.NotNull()
    java.text.SimpleDateFormat format, @org.jetbrains.annotations.NotNull()
    java.lang.String from) {
        return null;
    }
}