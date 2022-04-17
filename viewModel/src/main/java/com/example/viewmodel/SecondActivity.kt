package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondActivity : AppCompatActivity() {
    private lateinit var model: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        model = obtainViewModel(NewsViewModel::class.java)
        val textview = findViewById<TextView>(R.id.textview)
        textview.text = "text: ${model.newLiveData.value}"
    }
}