package com.example.suryashakthi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryashakthi.model.EnergyLog

class EnergyAdapter(private val logs: List<EnergyLog>) :
    RecyclerView.Adapter<EnergyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvGenerated: TextView = view.findViewById(R.id.tvGenerated)
        val tvConsumed: TextView = view.findViewById(R.id.tvConsumed)
        val tvSavings: TextView = view.findViewById(R.id.tvSavings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_energy, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val log = logs[position]

        holder.tvGenerated.text = "Generated: ${log.generated} kWh"
        holder.tvConsumed.text = "Consumed: ${log.consumed} kWh"
        holder.tvSavings.text = "Savings: ₹${log.savings}"
    }
}