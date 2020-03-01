package com.aliumujib.artic.remote.api


import com.aliumujib.artic.remote.models.DeviceInfo
import com.aliumujib.artic.remote.models.responses.CategoriesFetchResponse
import com.aliumujib.artic.remote.models.responses.CategoryDetailsResponse
import com.aliumujib.artic.remote.models.responses.PagedPostsListResponse
import com.aliumujib.artic.remote.models.responses.SinglePostResponse
import retrofit2.Call
import retrofit2.http.*

interface WordPressAPI {

    /* Category API transaction --------------------------- */

    @get:Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @get:GET("?json=get_category_index")
    val allCategories: Call<CategoriesFetchResponse>

    /* Post API transaction ------------------------------- */

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_posts$EXCLUDE_FIELD")
    suspend fun getPostByPage(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): PagedPostsListResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_post")
    fun getPostDetialsById(
        @Query("id") id: Int
    ): Call<SinglePostResponse>

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_search_results$EXCLUDE_FIELD")
    suspend fun getSearchPosts(
        @Query("search") search: String,
        @Query("count") count: Int
    ): PagedPostsListResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_category_posts$EXCLUDE_FIELD")
    fun getCategoryDetailsByPage(
        @Query("id") id: Int,
        @Query("page") page: Int,
        @Query("count") count: Int
    ): Call<CategoryDetailsResponse>

//    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
//    @GET("?json=respond/submit_comment")
//    fun sendComment(
//        @Query("post_id") post_id: Long,
//        @Query("name") name: String,
//        @Query("email") email: String,
//        @Query("content") content: String
//    ): Call<CallbackComment>

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @POST("?api-fcm=register")
    fun registerDevice(@Body deviceInfo: DeviceInfo): Call<Any>

    companion object {

        /* your wordPress url */
        val BASE_URL = "http://demo.dream-space.web.id/koran/"


        // minimize field for list of post
        const val EXCLUDE_FIELD = "&exclude=content,categories,tags,comments,custom_fields"
    }


}
