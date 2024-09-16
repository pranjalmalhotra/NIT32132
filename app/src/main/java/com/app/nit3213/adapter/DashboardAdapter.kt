package com.app.nit3213.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.nit3213.R
import com.app.nit3213.data.Entity

class DashboardAdapter(
    private val entity: List<Entity>,
    val context: Context,
    private val onItemClicked: (Entity) -> Unit
) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = entity[position]
        holder.tvProperty2.text = model.description
        holder.itemView.setOnClickListener {
            onItemClicked(model)
        }

    }

    override fun getItemCount(): Int {
        return entity.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvProperty2: TextView = itemView.findViewById(R.id.tvProperty2)

    }
}
