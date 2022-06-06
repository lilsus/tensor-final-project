package me.lilsus.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lilsus.data.FavoritesRepository
import me.lilsus.data.Translation
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {
    val translations = MutableLiveData<List<Translation>>()

    fun update() = CoroutineScope(Dispatchers.IO).launch {
        translations.postValue(repository.getTranslations())
    }

    fun deleteTranslation(translation: Translation) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteTranslation(translation)
        update()
    }
}