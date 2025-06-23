package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModel() {

    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() = coroutineScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        withContext(Dispatchers.IO) { repository.load() }
        liveDataWrapper.update(UiState.ShowData)
    }

    fun liveData() = liveDataWrapper.liveData()

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    companion object {

        fun provideFactory(
            liveDataWrapper: LiveDataWrapper,
            repository: Repository
        ) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel(liveDataWrapper = liveDataWrapper, repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}