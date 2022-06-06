package me.lilsus.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface TranslatorService {
    @GET("languages")
    suspend fun getLanguages(): LanguagesResponse

    @POST("translate")
    suspend fun translate(
        @Query("q") query: String,
        @Query("source") source: String,
        @Query("target") target: String
    ): TranslatorResponse
}