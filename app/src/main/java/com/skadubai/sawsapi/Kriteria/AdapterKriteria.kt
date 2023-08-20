package com.skadubai.sawsapi.Kriteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skadubai.sawsapi.Model.ModelKriteria
import com.skadubai.sawsapi.R

class AdapterKriteria(
    val Kriteria : ArrayList<ModelKriteria.dataKriteria>
): RecyclerView.Adapter<AdapterKriteria.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val kriteria = view.findViewById<TextView>(R.id.kriteria)
        val sifat = view.findViewById<TextView>(R.id.sifat)
        val bobot = view.findViewById<TextView>(R.id.bobot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_kriteria,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Kriteria[position]
        holder.kriteria.text = data.kriteria
        holder.sifat.text = data.sifat
        holder.bobot.text = data.bobot
    }

    override fun getItemCount() = Kriteria.size

    public fun setData(data: List<ModelKriteria.dataKriteria>){
        Kriteria.clear()
        Kriteria.addAll(data)
        notifyDataSetChanged()
    }

}
