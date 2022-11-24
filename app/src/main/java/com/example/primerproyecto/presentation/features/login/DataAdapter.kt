package com.example.primerproyecto.presentation.features.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.R


class DataAdapter internal constructor(context: Context?, names: ArrayList<String>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private val nameList: ArrayList<String>
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(context)
        nameList = names
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: String = nameList.get(position)
        holder.nomView.text = name
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nomView: TextView

        init {
            nomView = itemView.findViewById(R.id.nom)
        }
    }
}