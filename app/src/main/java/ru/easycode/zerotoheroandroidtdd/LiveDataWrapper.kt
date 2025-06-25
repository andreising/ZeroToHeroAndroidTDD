package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface LiveDataWrapper {
    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    fun liveData(): LiveData<UiState>

    interface Mutable : Update

    interface Update : LiveDataWrapper

    class Base(
        private val singleLineEvent: SingleLiveEvent<UiState> = SingleLiveEvent()
    ) : LiveDataWrapper {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            singleLineEvent.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            singleLineEvent.value = value
        }

        override fun liveData(): LiveData<UiState> {
            return singleLineEvent
        }

    }
}