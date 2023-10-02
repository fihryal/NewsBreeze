package com.faqiy.newsbreeze.data.network

import com.faqiy.newsbreeze.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getCommonNews(
        @Query("q") keyword: String = "common",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy:String = "publishedAt",
        @Query("pageSize") pageSize:Int = 30,
    ) : Call<NewsResponse>

    @GET("top-headlines")
    fun getEntertainmentNews(
        @Query("category") sources: String = "entertainment",
        @Query("language") keyword: String = "en",

    ) : Call<NewsResponse>

    @GET("top-headlines")
    fun getSportNews(
        @Query("category") sources: String = "sports",
        @Query("language") keyword: String = "en",
    ) : Call<NewsResponse>

    @GET("everything")
    fun getWarningNews(
        @Query("q") keyword: String = "warning",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy:String = "publishedAt",
        @Query("pageSize") pageSize:Int = 30,
    ) : Call<NewsResponse>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 50
    ): Call<NewsResponse>
}