package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class MainApp : Application() {
    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(ListLiveDataWrapper.Base())
    }
}