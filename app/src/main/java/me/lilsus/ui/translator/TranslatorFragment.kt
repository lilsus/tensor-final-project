package me.lilsus.ui.translator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import me.lilsus.R
import me.lilsus.TranslatorApplication
import me.lilsus.databinding.FragmentTranslatorBinding
import javax.inject.Inject

class TranslatorFragment : Fragment(R.layout.fragment_translator) {
    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var model: TranslatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTranslatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as TranslatorApplication).appComponent.inject(this)

        val sourceAdapter: ArrayAdapter<String> = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item)
        val targetAdapter: ArrayAdapter<String> = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item)

        binding.sourceLanguage.adapter = sourceAdapter
        binding.targetLanguage.adapter = targetAdapter

        model.languages.observe(viewLifecycleOwner) {
            sourceAdapter.clear()
            sourceAdapter.addAll(it.keys)
            sourceAdapter.notifyDataSetChanged()

            targetAdapter.clear()
            targetAdapter.addAll(it.keys)
            targetAdapter.notifyDataSetChanged()
        }

        model.sourceLanguage.observe(viewLifecycleOwner) {
            val position = sourceAdapter.getPosition(it)
            binding.sourceLanguage.setSelection(position)
        }

        model.sourceText.observe(viewLifecycleOwner) {
            binding.sourceText.setText(it)
        }

        model.targetLanguage.observe(viewLifecycleOwner) {
            val position = targetAdapter.getPosition(it)
            binding.targetLanguage.setSelection(position)
        }

        model.targetText.observe(viewLifecycleOwner) {
            binding.targetText.setText(it)
        }

        binding.translateButton.setOnClickListener {
            model.update(
                binding.sourceLanguage.selectedItem as String,
                binding.sourceText.text.toString(),
                binding.targetLanguage.selectedItem as String,
                binding.targetText.text.toString()
            )

            model.translate()
        }

        binding.swapButton.setOnClickListener {
            model.update(
                (binding.sourceLanguage.selectedItem ?: "") as String,
                binding.sourceText.text.toString(),
                (binding.targetLanguage.selectedItem ?: "") as String,
                binding.targetText.text.toString()
            )

            model.swap()
        }

        binding.clearButton.setOnClickListener {
            model.clear()
        }

        binding.saveButton.setOnClickListener {
            model.update(
                binding.sourceLanguage.selectedItem as String,
                binding.sourceText.text.toString(),
                binding.targetLanguage.selectedItem as String,
                binding.targetText.text.toString()
            )

            model.save()
        }

        model.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext().applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}