package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface UiState : Serializable {
    data class Base(val inputText: String, val textViewText: String) : UiState {
        companion object {
            fun getInitial() = Base("", "")
        }
    }
}