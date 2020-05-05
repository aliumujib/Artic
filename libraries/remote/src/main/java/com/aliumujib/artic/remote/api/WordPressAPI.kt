/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_category_index")
    suspend fun fetchAllCategories(): CategoriesFetchResponse

    /* Post API transaction ------------------------------- */

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_posts$EXCLUDE_FIELD")
    suspend fun getPostByPage(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): PagedPostsListResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_post")
    suspend fun getPostDetialsById(
        @Query("id") id: Int
    ): SinglePostResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_search_results$EXCLUDE_FIELD")
    suspend fun getSearchPosts(
        @Query("search") search: String,
        @Query("count") count: Int
    ): PagedPostsListResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=get_category_posts$EXCLUDE_FIELD")
    suspend fun getCategoryDetailsByPage(
        @Query("id") id: Int,
        @Query("page") page: Int,
        @Query("count") count: Int
    ): CategoryDetailsResponse

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @GET("?json=respond/submit_comment")
    suspend fun sendComment(
        @Query("post_id") post_id: Long,
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("content") content: String
    ): Any

    @Headers("Cache-Control: max-age=0", "User-Agent: Artic")
    @POST("?api-fcm=register")
    suspend fun registerDevice(@Body deviceInfo: DeviceInfo): Any

    companion object {

        /* your wordPress url */
        val BASE_URL = "http://demo.dream-space.web.id/koran/"


        // minimize field for list of post
        const val EXCLUDE_FIELD = "&exclude=tags,comments,custom_fields"
    }


}
