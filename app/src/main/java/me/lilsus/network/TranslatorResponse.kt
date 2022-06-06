package me.lilsus.network


import com.google.gson.annotations.SerializedName

data class TranslatorResponse(
    @SerializedName("translatedText")
    val translatedText: String
)