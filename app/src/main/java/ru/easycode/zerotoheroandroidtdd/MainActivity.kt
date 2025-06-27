package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewItem: RecyclerView

    private val textList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = savedInstanceState?.let {
            it.getStringArrayList(KEY) as ArrayList
        } ?: ArrayList(listOf())
        textList.addAll(list)
        initRecyclerView(textList)
        setButtonClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY, ArrayList(textList))
    }

    private fun setButtonClickListener() = with(binding) {
        actionButton.setOnClickListener {
            addNewItem(inputEditText.text.toString())
            inputEditText.setText("")
        }
    }

    private fun addNewItem(text: String) {
        textList.add(text)
        (recyclerViewItem.adapter as MainRecyclerViewAdapter).setList(textList)
    }

    private fun initRecyclerView(list: List<String>) = with(binding) {
        recyclerViewItem = recyclerView
        recyclerViewItem.adapter = MainRecyclerViewAdapter().apply { setList(list) }
    }

    companion object {
        private const val KEY = "key"
    }
}