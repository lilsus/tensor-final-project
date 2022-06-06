package me.lilsus.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.lilsus.network.Language
import me.lilsus.data.Translation
import javax.inject.Singleton

@Singleton
@Database(entities = [Language::class, Translation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun languageDao(): LanguageDao
    abstract fun translationDao(): TranslationDao
}