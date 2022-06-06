package me.lilsus.data

import me.lilsus.database.LanguageDao
import me.lilsus.network.TranslatorService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslatorRepository @Inject constructor(
    private val remoteDataSource: TranslatorService,
    private val localDataSource: LanguageDao
) {
    suspend fun getLanguages(): Map<String, String> {
        try {
            val languages = remoteDataSource.getLanguages()
            localDataSource.deleteAll()
            localDataSource.insertAll(*languages.toTypedArray())
        } finally {
            return localDataSource.getAll().associate { it.name to it.code }
        }
    }

    suspend fun translate(query: String, source: String, target: String): String =
        remoteDataSource.translate(query, source, target).translatedText
}