package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private val viewModel: MainViewModel by lazy {

        ViewModelProvider(
            owner = this,
            factory = MainViewModel.provideFactory(LiveDataWrapper.Base(), Repository.Base())
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observeLiveData()
        setButtonClickListener()
    }

    private fun initView() {
        button = findViewById<Button>(R.id.actionButton)
        textView = findViewById<TextView>(R.id.titleTextView)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
    }

    private fun setButtonClickListener() {
        button.setOnClickListener { viewModel.load() }
    }

    private fun observeLiveData() {
        viewModel.liveData().observe(this) { it.apply(button, textView, progressBar) }
    }
}