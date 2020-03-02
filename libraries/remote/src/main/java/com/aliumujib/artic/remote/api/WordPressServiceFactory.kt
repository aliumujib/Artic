package com.aliumujib.artic.remote.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WordPressServiceFactory {

    open fun makeWordPressService(isDebug: Boolean): WordPressAPI {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug))
        )
        return makeWordPressService(okHttpClient, Gson())
    }

    private fun makeWordPressService(okHttpClient: OkHttpClient, gson: Gson): WordPressAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://news.khuddam.ng/api/")
                .client(okHttpClient)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(WordPressAPI::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BASIC
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}