package com.example.emojiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emojis = listOf("😄", "🖥", "🥳", "🚀")
        var idx = 0

        val textView = findViewById<TextView>(R.id.emoji_text_view)
        textView.setOnClickListener {
            // idx = (idx + 1) % emojis.size
            if (idx == emojis.size) {
                idx = 0
            } else {
                idx++
            }
            textView.text = emojis[idx]
            Log.d("TextView", "TextView is clicked")
        }
    }
}