package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var uiState: UiState = UiState.getInitial()
    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button
    private lateinit var textView: TextView
    private val count = Count.Base(2, 4, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let { uiState = parseBundle(it) }
        initViews()
        setClickListeners()
        bindState(uiState)

    }

    private fun setClickListeners() {
        incrementButton.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            bindState(uiState)
        }
        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            bindState(uiState)
        }
    }

    private fun bindState(state: UiState) {
        state.let {
            when (it) {
                is UiState.Base -> bindBase(it)
                is UiState.Max -> bindMax(it)
                is UiState.Min -> bindMin(it)
            }
        }
    }

    private fun bindBase(baseState: UiState.Base) {
        clearEnabled()
        setTextView(baseState.text)
    }

    private fun bindMax(maxState: UiState.Max) {
        clearEnabled()
        setTextView(maxState.text)
        incrementButton.isEnabled = false
    }

    private fun bindMin(minState: UiState.Min) {
        clearEnabled()
        setTextView(minState.text)
        decrementButton.isEnabled = false
    }

    private fun clearEnabled() {
        decrementButton.isEnabled = true
        incrementButton.isEnabled = true
    }

    private fun setTextView(value: String) {
        textView.text = value
    }

    private fun initViews() {
        incrementButton = findViewById<Button>(R.id.incrementButton)
        decrementButton = findViewById<Button>(R.id.decrementButton)
        textView = findViewById<TextView>(R.id.countTextView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    private fun parseBundle(bundle: Bundle): UiState {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            bundle.getSerializable(KEY) as UiState
        }.let {
            when (it) {
                is UiState.Min -> UiState.Min(it.text)
                is UiState.Max -> UiState.Max(it.text)
                is UiState.Base -> UiState.Base(it.text)
            }
        }
    }

    companion object {
        private const val KEY = "key"
    }
}