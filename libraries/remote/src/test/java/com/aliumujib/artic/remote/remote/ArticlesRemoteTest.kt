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
package com.aliumujib.artic.remote.remote

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.impl.ArticlesRemote
import com.aliumujib.artic.remote.mapper.AuthorMapper
import com.aliumujib.artic.remote.mapper.CategoryMapper
import com.aliumujib.artic.remote.mapper.PostsMapper
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ArticlesRemoteTest {

    lateinit var api: WordPressAPI

    private lateinit var articlesRemote: ArticlesRemote
    private lateinit var postsMapper: PostsMapper

    private var categoryMapper = CategoryMapper()
    private var authorMapper = AuthorMapper()

    private lateinit var mockServer: MockWebServer


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mockServer = MockWebServer()
        // Start the local server
        mockServer.start()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(WordPressAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(WordPressAPI::class.java)

        postsMapper = PostsMapper(categoryMapper, authorMapper)
        articlesRemote = ArticlesRemote(api, postsMapper)

    }


    @Test
    fun `check that calling getPostByPage returns posts`() {
        val path = "?json=get_posts"

        val testObserver = TestObserver<List<ArticleEntity>>()
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJson("json/get_all_categories.json"))

        mockServer.enqueue(mockResponse)

        articlesRemote.getArticles(0).subscribe(testObserver)

        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val request = mockServer.takeRequest()
        assertEquals(path, request.path)
    }


    @Test
    fun `test that getPostByPage returns error when timeout is exceeded`() {
        val testObserver = TestObserver<List<ArticleEntity>>()

        val path = "/blogs"

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .throttleBody(1024, 1, TimeUnit.SECONDS) // Simulate SocketTimeout
            .setBody(getJson("json/get_all_categories.json"))

        mockServer.enqueue(mockResponse)
        articlesRemote.getArticles(0).subscribe(testObserver)

        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
        testObserver.assertNoValues()
        assertEquals(1, testObserver.errorCount())

        val request = mockServer.takeRequest()
        assertEquals(path, request.path)

    }

    fun stubAPIResponse() {
        whenever(api.getPostByPage(anyInt(), anyInt())).thenReturn(
            Observable.just(
                PagedPostsListResponse(
                    ArticlesRemote.PER_PAGE, 50,
                    3, DummyDataFactory.makePostList(30)
                )
            )
        )
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    @After
    @Throws
    fun tearDown() {
        // We're done with tests, shut it down
        mockServer.shutdown()
    }


}
