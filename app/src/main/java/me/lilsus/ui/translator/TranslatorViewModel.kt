package me.lilsus.ui.translator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lilsus.data.TranslatorRepository
import me.lilsus.data.FavoritesRepository
import me.lilsus.data.Translation
import javax.inject.Inject

class TranslatorViewModel @Inject constructor(
    private val translatorRepository: TranslatorRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    val sourceLanguage = MutableLiveData<String>()
    val sourceText = MutableLiveData<String>()
    val targetLanguage = MutableLiveData<String>()
    val targetText = MutableLiveData<String>()
    val languages = MutableLiveData<Map<String, String>>()
    val error = MutableLiveData<String>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                languages.postValue(translatorRepository.getLanguages())
            } catch (e: Exception) {
                error.postValue("Нет ответа от сервера")
            }
        }
    }

    fun update(sourceLanguage: String, sourceText: String, targetLanguage: String, targetText: String) {
        this.sourceLanguage.value = sourceLanguage
        this.sourceText.value = sourceText
        this.targetLanguage.value = targetLanguage
        this.targetText.value = targetText
    }

    fun translate() {
        if (sourceText.value.isNullOrEmpty() || sourceText.value.isNullOrBlank()) {
            error.value = "Введите текст"
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val source = languages.value?.get(sourceLanguage.value)
                val target = languages.value?.get(targetLanguage.value)
                targetText.postValue(translatorRepository.translate(sourceText.value ?: "", source ?: "", target ?: ""))
            } catch (e: Exception) {
                error.postValue("Нет ответа от сервера")
            }
        }
    }

    fun swap() {
        sourceLanguage.value = targetLanguage.value.also { targetLanguage.value = sourceLanguage.value }
        sourceText.value = targetText.value.also { targetText.value = sourceText.value }
    }

    fun clear() {
        sourceText.value = ""
        targetText.value = ""
    }

    fun save() {
        if (sourceText.value.isNullOrEmpty() || targetText.value.isNullOrEmpty()) {
            error.value = "Введите текст"
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            favoritesRepository.addTranslation(
                Translation(
                    sourceLanguage.value ?: "",
                    sourceText.value ?: "",
                    targetLanguage.value ?: "",
                    targetText.value ?: ""
                )
            )
        }
    }
}