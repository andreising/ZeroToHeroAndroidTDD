package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

sealed class UiState(val text: String) : Serializable {
    data class Base(val baseText: String): UiState(baseText)
    data class Max(val maxText: String): UiState(maxText)
}