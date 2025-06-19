package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

sealed class UiState(val mainText: String) : Serializable {
    data class Max(val text: String) : UiState(text)
    data class Min(val text: String) : UiState(text)
    data class Base(val text: String) : UiState(text)

    companion object {
        fun getInitial() = Min("0")
    }
}