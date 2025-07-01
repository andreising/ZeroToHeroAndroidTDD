package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    val listLiveDataWrapper: ListLiveDataWrapper
) : ViewModel() {

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }
}