package me.lilsus.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Translation(
    val sourceLanguage: String,
    val sourceText: String,
    val targetLanguage: String,
    val targetText: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)