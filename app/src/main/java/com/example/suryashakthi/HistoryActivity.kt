package com.example.suryashakthi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suryashakthi.data.AppDatabase
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val dao = AppDatabase.getDatabase(this).energyDao()

        lifecycleScope.launch {
            val logs = dao.getAllLogs()
            recyclerView.adapter = EnergyAdapter(logs)
        }
    }
}