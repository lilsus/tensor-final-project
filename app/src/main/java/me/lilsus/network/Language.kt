package me.lilsus.network


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Language(
    @PrimaryKey
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)