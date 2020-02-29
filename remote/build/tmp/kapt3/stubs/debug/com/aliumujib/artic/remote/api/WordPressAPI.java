package com.aliumujib.artic.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\f\u001a\u00020\nH\'J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\f\u001a\u00020\nH\'J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\'J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\f\u001a\u00020\nH\'J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\'R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Lcom/aliumujib/artic/remote/api/WordPressAPI;", "", "allCategories", "Lretrofit2/Call;", "Lcom/aliumujib/artic/remote/models/responses/CategoriesFetchResponse;", "getAllCategories", "()Lretrofit2/Call;", "getCategoryDetailsByPage", "Lcom/aliumujib/artic/remote/models/responses/CategoryDetailsResponse;", "id", "", "page", "count", "getPostByPage", "Lio/reactivex/Observable;", "Lcom/aliumujib/artic/remote/models/responses/PagedPostsListResponse;", "getPostDetialsById", "Lcom/aliumujib/artic/remote/models/responses/SinglePostResponse;", "getSearchPosts", "search", "", "registerDevice", "Lio/reactivex/Completable;", "deviceInfo", "Lcom/aliumujib/artic/remote/models/DeviceInfo;", "Companion", "remote_debug"})
public abstract interface WordPressAPI {
    public static final com.aliumujib.artic.remote.api.WordPressAPI.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXCLUDE_FIELD = "&exclude=content,categories,tags,comments,custom_fields";
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "?json=get_category_index")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract retrofit2.Call<com.aliumujib.artic.remote.models.responses.CategoriesFetchResponse> getAllCategories();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "?json=get_posts&exclude=content,categories,tags,comments,custom_fields")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract io.reactivex.Observable<com.aliumujib.artic.remote.models.responses.PagedPostsListResponse> getPostByPage(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "count")
    int count);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "?json=get_post")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract retrofit2.Call<com.aliumujib.artic.remote.models.responses.SinglePostResponse> getPostDetialsById(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "?json=get_search_results&exclude=content,categories,tags,comments,custom_fields")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract io.reactivex.Observable<com.aliumujib.artic.remote.models.responses.PagedPostsListResponse> getSearchPosts(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "search")
    java.lang.String search, @retrofit2.http.Query(value = "count")
    int count);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "?json=get_category_posts&exclude=content,categories,tags,comments,custom_fields")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract retrofit2.Call<com.aliumujib.artic.remote.models.responses.CategoryDetailsResponse> getCategoryDetailsByPage(@retrofit2.http.Query(value = "id")
    int id, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "count")
    int count);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "?api-fcm=register")
    @retrofit2.http.Headers(value = {"Cache-Control: max-age=0", "User-Agent: Artic"})
    public abstract io.reactivex.Completable registerDevice(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.aliumujib.artic.remote.models.DeviceInfo deviceInfo);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/aliumujib/artic/remote/api/WordPressAPI$Companion;", "", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "EXCLUDE_FIELD", "remote_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        private static final java.lang.String BASE_URL = "http://demo.dream-space.web.id/koran/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EXCLUDE_FIELD = "&exclude=content,categories,tags,comments,custom_fields";
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBASE_URL() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}