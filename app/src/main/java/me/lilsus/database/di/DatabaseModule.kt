package me.lilsus.database.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.lilsus.database.AppDatabase
import me.lilsus.database.LanguageDao
import me.lilsus.database.TranslationDao
import javax.inject.Singleton

@Module
class DatabaseModule(val application: Application) {
    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "translator"
        ).build()

    @Singleton
    @Provides
    fun provideTranslationDao(database: AppDatabase): TranslationDao = database.translationDao()

    @Singleton
    @Provides
    fun provideLanguageDao(database: AppDatabase): LanguageDao = database.languageDao()
}