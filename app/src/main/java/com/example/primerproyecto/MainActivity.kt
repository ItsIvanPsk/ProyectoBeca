package com.example.primerproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonView = findViewById(R.id.main_button_change) as Button
        val textView = findViewById(R.id.main_text_header) as TextView
        buttonView.setOnClickListener {
            textView.text = "El texto ha cambiado!"
        }
    }
}