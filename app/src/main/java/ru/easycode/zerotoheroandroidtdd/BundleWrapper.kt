package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {

    interface Mutable : Save, Restore
    interface Save : BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    class BaseRestore(private val bundle: Bundle) : Restore {
        override fun restore(): UiState {
            return bundle.getSerializable(KEY) as UiState
        }
    }

    class BaseSave(private val bundle: Bundle) : Save {
        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY, uiState)
        }

    }

    companion object {
        private const val KEY = "key"
    }
}