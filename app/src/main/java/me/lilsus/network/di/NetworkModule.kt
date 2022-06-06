package me.lilsus.network.di

import dagger.Module
import dagger.Provides
import me.lilsus.network.TranslatorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideTranslatorService(): TranslatorService =
        Retrofit.Builder()
            .baseUrl("https://libretranslate.de/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TranslatorService::class.java)

}