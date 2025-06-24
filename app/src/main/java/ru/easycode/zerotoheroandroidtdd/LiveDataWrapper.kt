package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>
    fun save(bundleWrapper: BundleWrapper.Save)

    class Base(
        private val liveData: MutableLiveData<UiState> = SingleLiveEvent<UiState>()
    ) : LiveDataWrapper {

        init {
            update(UiState.Initial)
        }
        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData
        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(liveData.value ?: UiState.Initial)
        }

    }
}