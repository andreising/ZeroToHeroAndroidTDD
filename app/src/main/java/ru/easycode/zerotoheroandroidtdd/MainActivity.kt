package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.actionButton)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textView = findViewById<TextView>(R.id.titleTextView)
        button.setOnClickListener {
            thread {
                runOnUiThread { onLoad() }
                Thread.sleep(1000)
                runOnUiThread { loadDone() }
            }
        }
    }

    private fun onLoad() {
        button.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    private fun loadDone() {
        button.isEnabled = true
        progressBar.visibility = View.GONE
        textView.visibility = View.VISIBLE
    }
}