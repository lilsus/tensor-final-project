package me.lilsus

import android.app.Application
import me.lilsus.database.di.DatabaseModule
import me.lilsus.di.DaggerTranslatorComponent
import me.lilsus.data.di.TranslatorModule
import me.lilsus.network.di.NetworkModule


class TranslatorApplication : Application() {
    val appComponent = DaggerTranslatorComponent.builder()
        .databaseModule(DatabaseModule(this))
        .networkModule(NetworkModule())
        .translatorModule(TranslatorModule())
        .build()
}