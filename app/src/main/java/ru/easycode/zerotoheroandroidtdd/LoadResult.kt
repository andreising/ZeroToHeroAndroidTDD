package ru.easycode.zerotoheroandroidtdd

abstract class LoadResult {
    abstract fun show(updateLiveData: LiveDataWrapper.Update)
    abstract val text: String

    data class Success(val data: SimpleResponse) : LoadResult() {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }

        override val text: String
            get() = data.text
    }

    data class Error(val noConnection: Boolean) : LoadResult() {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(
                UiState.ShowData(text)
            )
        }

        override val text: String
            get() = if (noConnection) "No internet connection"
            else "Something went wrong"
    }
}