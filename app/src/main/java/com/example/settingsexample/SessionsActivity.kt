package com.example.settingsexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SettingsActivity : AppCompatActivity() {

    private val viewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sessions_activity)

        val list = findViewById<RecyclerView>(R.id.list)
        list.itemAnimator = null;
        val adapter = SessionAdapter()
        list.adapter = adapter

        viewModel.sessions.observe(this, {
            adapter.submitList(it)
        })
    }
}