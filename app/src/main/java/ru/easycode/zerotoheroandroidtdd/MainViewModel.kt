package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModel() {

    val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    fun load() = viewModelScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        liveDataWrapper.update(UiState.ShowData(repository.load().text))
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    fun liveData() = liveDataWrapper.liveData()
}