package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var containTextView = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.removeButton).setOnClickListener {
            removeView()
            containTextView = false
        }
    }

    private fun removeView() {
        val container = findViewById<LinearLayout>(R.id.rootLayout)
        val textView = findViewById<TextView>(R.id.titleTextView)
        container.removeView(textView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(VISIBILITY_KEY, containTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        containTextView = savedInstanceState.getBoolean(VISIBILITY_KEY)
        if (!containTextView) removeView()
    }

    companion object {
        private const val VISIBILITY_KEY = "visibility_key"
    }
}