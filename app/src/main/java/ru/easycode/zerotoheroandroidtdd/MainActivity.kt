package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw IllegalStateException()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.let {
            val currentUiState =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) it.getSerializable(
                    KEY,
                    UiState.Base::class.java
                ) as UiState.Base
                else it.getSerializable(KEY) as UiState.Base
            binding.titleTextView.text = currentUiState.textViewText
            binding.inputEditText.setText(currentUiState.inputText)
        }
        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text.toString()
            binding.inputEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(
            KEY,
            UiState.Base(
                binding.inputEditText.text.toString(),
                binding.titleTextView.text.toString()
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val KEY = "key"
    }
}