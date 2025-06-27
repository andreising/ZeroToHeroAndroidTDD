package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw IllegalStateException()

    private lateinit var textKeeper: TextKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textKeeper = savedInstanceState?.let {
            it.getSerializable(KEY, TextKeeper::class.java) as TextKeeper
        } ?: TextKeeper()
        textKeeper.names().let {
            if (it.isNotEmpty()) it
                .forEach { addTextView(it) }
        }
        binding.actionButton.setOnClickListener {
            val newText = binding.inputEditText.text.toString()
            addTextView(newText)
            textKeeper.setNextText(newText)
            binding.inputEditText.setText("")
        }
    }

    private fun addTextView(newText: String) = with(binding) {
        contentLayout.addView(TextView(this@MainActivity).apply {
            text = newText
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, textKeeper)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val KEY = "key"
    }
}