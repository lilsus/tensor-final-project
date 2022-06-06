package me.lilsus.di

import dagger.Component
import me.lilsus.data.di.TranslatorModule
import me.lilsus.database.di.DatabaseModule
import me.lilsus.network.di.NetworkModule
import me.lilsus.ui.favorites.FavoritesFragment
import me.lilsus.ui.translator.TranslatorFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [TranslatorModule::class, NetworkModule::class, DatabaseModule::class])
interface TranslatorComponent {
    fun inject(fragment: TranslatorFragment)

    fun inject(fragment: FavoritesFragment)
}