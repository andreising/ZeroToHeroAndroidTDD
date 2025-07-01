package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {
    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)

    class Base : ListLiveDataWrapper {

        private val liveData = MutableLiveData<List<CharSequence>>()

        override fun liveData(): LiveData<List<CharSequence>> = liveData

        override fun add(new: CharSequence) {
            val newList = mutableListOf<CharSequence>().apply {
                addAll(liveData.value ?: emptyList())
                add(new)
            }
            liveData.value = newList
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save((liveData.value?.toList() ?: error("")) as ArrayList<CharSequence>)
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }

    }

}

