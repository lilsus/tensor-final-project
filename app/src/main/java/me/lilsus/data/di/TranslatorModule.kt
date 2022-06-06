package me.lilsus.data.di

import dagger.Module
import dagger.Provides
import me.lilsus.data.FavoritesRepository
import me.lilsus.database.LanguageDao
import me.lilsus.database.TranslationDao
import me.lilsus.data.TranslatorRepository
import me.lilsus.network.TranslatorService
import javax.inject.Singleton

@Module
class TranslatorModule {
    @Singleton
    @Provides
    fun provideTranslatorRepository(
        remoteDataSource: TranslatorService,
        localDataSource: LanguageDao
    ): TranslatorRepository = TranslatorRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideFavoritesRepository(localDataSource: TranslationDao): FavoritesRepository =
        FavoritesRepository(localDataSource)
}