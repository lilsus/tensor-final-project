package me.lilsus.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.lilsus.data.Translation


@Dao
interface TranslationDao {
    @Query("SELECT * FROM translation")
    fun getAll(): List<Translation>

    @Insert
    fun insertAll(vararg translation: Translation)

    @Delete
    fun delete(translation: Translation)
}