package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var state: State = State.Initial
        set(value) {
            field = value
            field.apply()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.removeButton)
        val textView = findViewById<TextView>(R.id.titleTextView)
        val linearLayout = findViewById<LinearLayout>(R.id.rootLayout)
        savedInstanceState?.let {
            var result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(KEY, State::class.java) as State
            } else {
                it.getSerializable(KEY) as State
            }
            if (result is State.Removed) result = State.Removed(linearLayout, textView, button)
            setNewState(result)
        }
        button.setOnClickListener {
            setNewState(State.Removed(linearLayout, textView, button))
        }
    }

    private fun setNewState(state: State) { this.state = state }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    companion object {
        const val KEY = "key"
    }
}

interface State : Serializable {
    fun apply() = Unit

    data object Initial : State

    data class Removed(
        private val linearLayout: LinearLayout,
        private val textView: TextView,
        private val button: Button
    ) : State {
        override fun apply() {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }

    }
}