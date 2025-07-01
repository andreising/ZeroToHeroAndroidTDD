package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: MainRecyclerViewAdapter

    private val viewModel by lazy { (applicationContext as MainApp).viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.let {
            viewModel.restore(BundleWrapper.Base(it))
        }
        initRecyclerView()
        observeStrings()
        setButtonClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    private fun setButtonClickListener() = with(binding) {
        actionButton.setOnClickListener {
            addNewItem(inputEditText.text.toString())
            inputEditText.setText("")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun observeStrings() {
        viewModel.listLiveDataWrapper.liveData().observe(this) {
            recyclerViewAdapter.setList(it as List<String>)
        }
    }

    private fun addNewItem(text: String) {
        viewModel.add(text)
    }

    private fun initRecyclerView() = with(binding) {
        recyclerViewAdapter = MainRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }

}