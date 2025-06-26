package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.io.Serializable

interface UiState : Serializable {

    data class Base(val inputText: String, val textViewText: String) : UiState {
        fun apply(textView: TextView, textInputEditText: TextInputEditText, button: Button) {
            textView.text = textViewText
            textInputEditText.setText(inputText)
            button.isEnabled = textViewText.length >= 3
        }
    }
}