package me.lilsus.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.lilsus.network.Language
import javax.inject.Singleton

@Singleton
@Dao
interface LanguageDao {
    @Query("SELECT * FROM language")
    suspend fun getAll(): List<Language>

    @Insert
    suspend fun insertAll(vararg languages: Language)

    @Query("DELETE FROM language")
    suspend fun deleteAll()
}