package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(button: Button, textView: TextView, progressBar: ProgressBar)

    data object Initial : UiState {
        private fun readResolve(): Any = Initial
        override fun apply(
            button: Button,
            textView: TextView,
            progressBar: ProgressBar
        ) {
            button.isEnabled = true
            textView.visibility = View.GONE
            progressBar.visibility = View.GONE
        }

    }

    data object ShowProgress : UiState {
        private fun readResolve(): Any = ShowProgress
        override fun apply(
            button: Button,
            textView: TextView,
            progressBar: ProgressBar
        ) {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
        }
    }

    data object ShowData : UiState {
        private fun readResolve(): Any = ShowData
        override fun apply(
            button: Button,
            textView: TextView,
            progressBar: ProgressBar
        ) {
            button.isEnabled = true
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }
}