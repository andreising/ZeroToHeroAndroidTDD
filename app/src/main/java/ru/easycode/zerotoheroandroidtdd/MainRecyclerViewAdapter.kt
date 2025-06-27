package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ElementTextViewLayoutBinding

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var textList = listOf<String>()

    fun setList(list: List<String>) {
        textList = list
        notifyItemInserted(list.size - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding = ElementTextViewLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
        holder.setNewItem(textList[position])
    }

    override fun getItemCount(): Int {
        return textList.size
    }

}