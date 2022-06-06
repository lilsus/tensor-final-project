package me.lilsus.data

import me.lilsus.database.TranslationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(
    private val localDataSource: TranslationDao
) {
    fun getTranslations(): List<Translation> = localDataSource.getAll()

    suspend fun addTranslation(translation: Translation) {
        localDataSource.insertAll(translation)
    }

    suspend fun deleteTranslation(translation: Translation) {
        localDataSource.delete(translation)
    }
}