package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

class TextKeeper() : Serializable {
    private val names = mutableListOf<String>()

    fun setNextText(text: String) {
        names.add(text)
    }

    fun names(): List<String> = names
}