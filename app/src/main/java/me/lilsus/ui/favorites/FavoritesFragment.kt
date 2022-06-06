package me.lilsus.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.lilsus.TranslatorApplication
import me.lilsus.databinding.FragmentFavoritesBinding
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var model: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as TranslatorApplication).appComponent.inject(this)

        val adapter = FavoritesListAdapter(model::deleteTranslation)
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))

        model.translations.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }

    override fun onResume() {
        super.onResume()
        model.update()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}