package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var buttonView: Button
    private var uiState: UiState = UiState.Base("0")

    private val count = Count.Base(step = 2, max = 4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let {
            uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                it.getSerializable(KEY) as UiState
            }.let {
                uiState = when (it) {
                    is UiState.Base -> it
                    is UiState.Max -> it
                }
                uiState
            }
        }
        textView = findViewById<TextView>(R.id.countTextView)
        buttonView = findViewById<Button>(R.id.incrementButton)
        bindByState(uiState)
        buttonView.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            bindByState(uiState)
        }
    }

    private fun bindByState(uiState: UiState) {
        uiState.let {
            when(it) {
                is UiState.Base -> bindBase(it)
                is UiState.Max -> bindMax(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY,uiState)
    }

    private fun bindBase(baseState: UiState.Base) {
        textView.text = baseState.text
    }

    private fun bindMax(maxState: UiState.Max) {
        textView.text = maxState.text
        buttonView.isEnabled = false
    }

    companion object {
        private const val KEY = "key"
    }
}