package ru.easycode.zerotoheroandroidtdd

import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ElementTextViewLayoutBinding

class MainViewHolder(private val binding: ElementTextViewLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setNewItem(text: String) {
        binding.elementTextView.text = text
    }
}