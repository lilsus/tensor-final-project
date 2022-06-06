package me.lilsus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import me.lilsus.R
import me.lilsus.databinding.ActivityMainBinding
import me.lilsus.ui.favorites.FavoritesFragment
import me.lilsus.ui.translator.TranslatorFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val translator = TranslatorFragment()
        val favorites = FavoritesFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            val fragment: Fragment = when (it.itemId) {
                R.id.translator -> translator
                else -> favorites
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
            true
        }

        setContentView(binding.root)
    }
}