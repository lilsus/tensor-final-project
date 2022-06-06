package me.lilsus.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.lilsus.databinding.FavoritesListItemBinding
import me.lilsus.data.Translation

class FavoritesListAdapter(
    val deleteAction: (Translation) -> Unit
): RecyclerView.Adapter<FavoritesListAdapter.FavoritesListViewHolder>() {
    class FavoritesListViewHolder(val binding: FavoritesListItemBinding): RecyclerView.ViewHolder(binding.root)

    private val translations = mutableListOf<Translation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesListViewHolder {
        val binding = FavoritesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesListViewHolder, position: Int) {
        val translation = translations[position]

        with(holder) {
            binding.sourceLanguage.text = translation.sourceLanguage
            binding.sourceText.text = translation.sourceText
            binding.targetLanguage.text = translation.targetLanguage
            binding.targetText.text = translation.targetText

            binding.deleteButton.setOnClickListener {
                deleteAction(translation)
            }
        }
    }

    override fun getItemCount(): Int = translations.size

    fun update(data: List<Translation>) {
        translations.clear()
        translations.addAll(data)
        notifyDataSetChanged()
    }
}