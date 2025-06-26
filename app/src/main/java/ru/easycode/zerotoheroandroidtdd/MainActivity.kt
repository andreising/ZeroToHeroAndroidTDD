package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
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
            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(KEY, UiState.Base::class.java) as UiState.Base
            } else {
                it.getSerializable(KEY) as UiState.Base
            }
            with(binding) { result.apply(titleTextView, inputEditText, actionButton) }
        }
        with(binding) {
            inputEditText.doAfterTextChanged {
                actionButton.isEnabled = it.toString().length >= 3
            }
            actionButton.setOnClickListener {
                titleTextView.text = inputEditText.text
                inputEditText.setText("")
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) = with(binding) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(
            KEY,
            UiState.Base(inputEditText.text.toString(), titleTextView.text.toString())
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