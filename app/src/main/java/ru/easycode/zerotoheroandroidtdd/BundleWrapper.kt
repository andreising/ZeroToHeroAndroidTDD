package ru.easycode.zerotoheroandroidtdd

interface BundleWrapper {

    interface Mutable : Save, Restore
    interface Save : BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }
}