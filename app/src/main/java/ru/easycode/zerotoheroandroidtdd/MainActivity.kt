package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.incrementButton)
        textView = findViewById<TextView>(R.id.countTextView)
        savedInstanceState?.let {
            textView.text = it.getString(KEY)
        }
        button.setOnClickListener {
            textView.text = Count.Base(step = 2).increment(textView.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, textView.text.toString())
    }

    companion object {
        const val KEY = "key"
    }
}